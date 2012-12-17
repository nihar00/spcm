package com.me.src.doctor;

import javax.servlet.http.HttpSession;

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

import com.me.src.dao.ConsentDao;
import com.me.src.dao.ConsentRequestDao;
import com.me.src.dao.HospitalDao;
import com.me.src.dao.PatientDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.ConsentRequest;
import com.me.src.pojo.Hospital;
import com.me.src.pojo.Patient;
import com.me.src.pojo.UserAccount;
import com.me.src.pojo.command.RecordRequestCommand;

@Controller
@RequestMapping("/request-record.htm")
//nihar 4 changes
@SessionAttributes("patient")
//nihar 4 changes
public class RequestRecordController {
	private static final Logger logger = LoggerFactory.getLogger(RequestRecordController.class);
		
	@Autowired
	ConsentRequestDao consentRequestDao;
	@Autowired
	PatientDao patientDao;
	@Autowired
	UserAccountDao userAccountDao;
	@Autowired
	ConsentDao consentDao;
	@Autowired
	HospitalDao hospitalDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model, @RequestParam("patientId") long patientId) {
		RecordRequestCommand request = new RecordRequestCommand();
		//nihar 4 changes
		Patient patient = patientDao.findById(patientId);
		model.addAttribute("hospitalList",hospitalDao.findAll());
		//nihar 4 changes
		
		model.addAttribute("request", request);
		
		//nihar 4 changes
		model.addAttribute("patient", patient);
		logger.info("for patient: " + patient.getId());
		//nihar 4 changes
		
		return "doctor/record-request";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("request") RecordRequestCommand request,@ModelAttribute("patient") Patient patient, BindingResult result,  @RequestParam("hospitalId") long hospitalId,SessionStatus status,HttpSession session,Model model) {		
	
		//nihar 4 changes
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");
		Hospital h=hospitalDao.findById(hospitalId);
		logger.info("Record Request: " + request.getPatientId());
		logger.info("PatientId in consent creation by doctor request: " + patient.getId());
		logger.info( " user: " + ua.getPerson().getFirstName());
		logger.info("Hospital record is requested " + h.getName());
		
		ConsentRequest consentRequest = new ConsentRequest();
		consentRequest.setPatient(patientDao.findById( request.getPatientId()));
		consentRequest.setRecordProvider(h);
		consentRequest.setRecordRequester(hospitalDao.findById(ua.getPerson().getHospital().getId()));
		consentRequest.setRequestByUser(ua);
		
		//nihar 4 changes
		
		consentRequestDao.saveOrUpdate(consentRequest);
		model.addAttribute("patientlist", patientDao.listPatient(ua.getPerson().getHospital().getId()));
		return "doctor/home";
	}
	
}
