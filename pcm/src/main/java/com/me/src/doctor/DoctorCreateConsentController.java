package com.me.src.doctor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.ValidationErrorList;
import org.owasp.esapi.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.me.src.dao.ConsentDao;
import com.me.src.dao.HospitalDao;
import com.me.src.dao.PatientDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Consent;
import com.me.src.pojo.Patient;
import com.me.src.pojo.UserAccount;
import com.me.src.pojo.command.ConsentCommand;

@Controller
@RequestMapping("/doctor")
//nihar 4 changes
@SessionAttributes("patient")
//nihar 4 changes
public class DoctorCreateConsentController {
	private static final Logger logger = LoggerFactory.getLogger(DoctorCreateConsentController.class);

	@Autowired
	PatientDao patientDao;
	@Autowired
	UserAccountDao userAccountDao;
	@Autowired
	ConsentDao consentDao;
	@Autowired
	HospitalDao hospitalDao;

	@RequestMapping(value = "/create-consent.htm", method = RequestMethod.GET)
	public String initForm(ModelMap model,  @RequestParam("patientId") long patientId) {
		ConsentCommand consentCommand = new ConsentCommand();
		//nihar 4 changes
		Patient patient = patientDao.findById(patientId);
		//nihar 4 changes

		model.addAttribute("consent", consentCommand);

		//nihar 4 changes
		model.addAttribute("patient", patient);
		logger.info("for patient: " + patient.getId());
		//nihar 4 changes

		return "doctor/create-consent";
	}

	@RequestMapping(value = "/create-consent.htm", method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("consent") ConsentCommand consentCommand,@ModelAttribute("patient") Patient patient, BindingResult result, SessionStatus status,HttpSession session,Model model) {		

		Consent consent = new Consent();
		consent.setConsentType(consentCommand.getConsentType());
		
		long recordTypes = 0;
		for(String s : consentCommand.getRecordType()) {
			long record = Long.parseLong(s);
			recordTypes |= record;
		}
		
		consent.setRecordType(Long.toString(recordTypes));
		logger.info("consent Type: " + consent.getConsentType() + " recordType: " + consent.getRecordType());

		//nihar 4 changes
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");		
		logger.info("PatientId in consent creation by doctor: " + patient.getId());
		logger.info( " user: " + ua.getPerson().getFirstName());
		
		//Meena's validation Changes
	    ValidationErrorList errorList = consent.validate();
		if (!errorList.isEmpty()) 
		{
			ArrayList<String> errorlist = new ArrayList<String>();
			for (Object vo : errorList.errors()) 
			{
				  ValidationException ve = (ValidationException)vo;
				  errorlist.add( ESAPI.encoder().encodeForHTML(ve.getMessage()) );
				  logger.info("Error " + ESAPI.encoder().encodeForHTML(ve.getMessage()));
			}
		    ModelAndView modelAndView = new ModelAndView("doctor/create-consent");
		    modelAndView.addObject("errorlist", errorlist);
		    return modelAndView;
		}
		
		consent.setDate(new Date());
		consent.setConsentCreatedBy(userAccountDao.findById(ua.getPerson().getId()));
		consent.setPatient(patientDao.findById(patient.getId()));
		consent.setHospital(hospitalDao.findById(ua.getPerson().getHospital().getId()));
		//nihar 4 changes
		
		//new addition
		Consent c=consentDao.getConsentFromPatientId(patient.getId());
		//new addition
		
		if(c==null)
		{
		consentDao.saveOrUpdate(consent);
		}
		else
		{
			consent.setId(c.getId());
			consentDao.saveOrUpdate(consent);	
			insertConsentRecordBeforeUpdateToFile(consent);
		}
		
		model.addAttribute("patientlist", patientDao.listPatient(ua.getPerson().getHospital().getId()));
		return new ModelAndView("doctor/home");
	}
	
	private void insertConsentRecordBeforeUpdateToFile(Consent consent){
		File file =new File("Consent.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fileWritter;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			fileWritter = new FileWriter(file.getName(),true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(System.getProperty( "line.separator" )+"Consent ID :"+consent.getId());
			bufferWritter.write(System.getProperty( "line.separator" )+"Updated By: "+consent.getConsentCreatedBy().getUsername());
			bufferWritter.write(System.getProperty( "line.separator" )+"Date Accessed on: "+dateFormat.format(date));
			bufferWritter.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
