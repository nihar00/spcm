package com.me.src.doctor;

import java.util.Date;

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
import com.me.src.dao.HospitalDao;
import com.me.src.dao.PatientDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Consent;
import com.me.src.pojo.Patient;
import com.me.src.pojo.UserAccount;

@Controller
@RequestMapping("/create-consent.htm")
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

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model,  @RequestParam("patientId") long patientId) {
		Consent consent = new Consent();
		//nihar 4 changes
		Patient patient = patientDao.findById(patientId);
		//nihar 4 changes

		model.addAttribute("consent", consent);

		//nihar 4 changes
		model.addAttribute("patient", patient);
		logger.info("for patient: " + patient.getId());
		//nihar 4 changes

		return "doctor/create-consent";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("consent") Consent consent,@ModelAttribute("patient") Patient patient, BindingResult result, SessionStatus status,HttpSession session,Model model) {		

		//nihar 4 changes
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");
		logger.info("Consent: " + consent.getConsentType());
		logger.info("PatientId in consent creation by doctor: " + patient.getId());
		logger.info( " user: " + ua.getPerson().getFirstName());

		consent.setDate(new Date());
		consent.setConsentCreatedBy(userAccountDao.findById( ua.getPerson().getId()));
		consent.setPatient(patientDao.findById(patient.getId()));
		consent.setHospital(hospitalDao.findById(ua.getPerson().getHospital().getId()));
		//nihar 4 changes
		
		consentDao.saveOrUpdate(consent);
		
		model.addAttribute("patientlist", patientDao.listPatient(ua.getPerson().getHospital().getId()));
		return "doctor/home";
	}

}
