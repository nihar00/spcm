package com.me.src.patient;

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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/patient")
public class PatientCreateConsentController {
	private static final Logger logger = LoggerFactory.getLogger(PatientCreateConsentController.class);

	@Autowired
	PatientDao patientDao;
	@Autowired
	UserAccountDao userAccountDao;
	@Autowired
	ConsentDao consentDao;
	@Autowired
	HospitalDao hospitalDao;

	@RequestMapping(value = "/create-consent.htm", method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		ConsentCommand consentCommand = new ConsentCommand();
		model.addAttribute("consent", consentCommand);
		return "patient/create-consent";
	}

	@RequestMapping(value = "/create-consent.htm", method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("consent") ConsentCommand consentCommand, BindingResult result, SessionStatus status,HttpSession session) {		

		Consent consent = new Consent();
		consent.setConsentType(consentCommand.getConsentType());
		long recordTypes = 0;
		for(String s : consentCommand.getRecordType()) {
			long record = Long.parseLong(s);
			recordTypes |= record;
		}

		consent.setRecordType(Long.toString(recordTypes));
		logger.info("Consent: " + consent.getConsentType() + " recordType: " + consent.getRecordType());

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
			ModelAndView modelAndView = new ModelAndView("patient/create-consent");
			modelAndView.addObject("errorlist", errorlist);
			return modelAndView;
		} 
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");
		consent.setDate(new Date());
		consent.setConsentCreatedBy(ua);
		consent.setPatient(patientDao.getPatientFromPersonId(ua.getPerson().getId()));
		consent.setHospital(hospitalDao.findById(ua.getPerson().getHospital().getId()));	
		//new addition
		Patient pat=patientDao.getPatientFromPersonId(ua.getPerson().getId());
		Consent c=consentDao.getConsentFromPatientId(pat.getId());
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
		return new ModelAndView("patient/home");
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
