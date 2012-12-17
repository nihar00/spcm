package com.me.src.patient;

import java.util.Date;

import javax.servlet.http.HttpSession;

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
import com.me.src.dao.HospitalDao;
import com.me.src.dao.PatientDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Consent;
import com.me.src.pojo.UserAccount;

@Controller
@RequestMapping("/patient-create-consent.htm")
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
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		Consent consent = new Consent();
		model.addAttribute("consent", consent);
		return "patient/create-consent";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("consent") Consent consent, BindingResult result, SessionStatus status,HttpSession session) {		
	
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");
		logger.info("Consent: " + consent.getConsentType());
		consent.setDate(new Date());
		consent.setConsentCreatedBy(ua);
		consent.setPatient(patientDao.getPatientFromPersonId(ua.getPerson().getId()));
		consent.setHospital(hospitalDao.findById(ua.getPerson().getHospital().getId()));		
		consentDao.saveOrUpdate(consent);
		
		return "patient/home";
	}
	
}
