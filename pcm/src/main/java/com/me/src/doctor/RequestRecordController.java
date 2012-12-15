package com.me.src.doctor;

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

import com.me.src.dao.ConsentDao;
import com.me.src.dao.ConsentRequestDao;
import com.me.src.dao.HospitalDao;
import com.me.src.dao.PatientDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.ConsentRequest;
import com.me.src.pojo.command.RecordRequestCommand;

@Controller
@RequestMapping("/request-record.htm")
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
	public String initForm(ModelMap model) {
		RecordRequestCommand request = new RecordRequestCommand();
		model.addAttribute("request", request);
		return "doctor/record-request";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("request") RecordRequestCommand request, BindingResult result, SessionStatus status) {		
	
		logger.info("Record Request: " + request.getPatientId());
		
		ConsentRequest consentRequest = new ConsentRequest();
		consentRequest.setPatient(patientDao.findById(1));
		consentRequest.setRecordProvider(hospitalDao.findById(1));
		consentRequest.setRecordRequester(hospitalDao.findById(1));
		consentRequest.setRequestByUser(userAccountDao.findById(2));
		
		consentRequestDao.saveOrUpdate(consentRequest);
		return "doctor/home";
	}
	
}
