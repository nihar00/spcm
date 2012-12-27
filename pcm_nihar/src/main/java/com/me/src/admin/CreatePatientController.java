package com.me.src.admin;

import java.util.ArrayList;

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

import com.me.src.dao.PatientDao;
import com.me.src.dao.PersonDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Role;
import com.me.src.pojo.UserAccount;
import com.me.src.pojo.command.CreatePatient;
import com.me.src.security.HashGenerator;

@Controller
@RequestMapping("/admin")
public class CreatePatientController {
	private static final Logger logger = LoggerFactory.getLogger(CreatePatientController.class);

	@Autowired
	PatientDao patientDao;
	@Autowired
	PersonDao personDao;
	@Autowired
	UserAccountDao userAccountDao;

	
	@RequestMapping(value = "/create-patient.htm", method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		CreatePatient createPatient = new CreatePatient();
		model.addAttribute("createPatient", createPatient);
		return "admin/create-patient";
	}

	@RequestMapping(value = "/create-patient.htm", method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("createPatient") CreatePatient createPatient, BindingResult result, SessionStatus status, HttpSession session) {		

		logger.info("Patient Name: " +createPatient.getPatient().getPerson().getFirstName());


		//Meena's validation Changes
		ValidationErrorList errorList = createPatient.validate();
	     if (!errorList.isEmpty()) 
	     {
			ArrayList<String> errorlist = new ArrayList<String>();
			for (Object vo : errorList.errors()) 
			{
		         ValidationException ve = (ValidationException)vo;
		         errorlist.add( ESAPI.encoder().encodeForHTML(ve.getMessage()) );
		         logger.info("Error " + ESAPI.encoder().encodeForHTML(ve.getMessage()));
		    }
			ModelAndView modelAndView = new ModelAndView("admin/create-patient");
		    modelAndView.addObject("errorlist", errorlist);
		    return modelAndView;
		}
	     
		if(userAccountDao.isUsernameExist(createPatient.getUserAccount().getUsername().toLowerCase())) {
			result.rejectValue("userAccount.username","","Username already exist");
			return new ModelAndView("admin/create-patient");
			
		}
		
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");		
		
		//nihar 5 changes
		createPatient.getUserAccount().setPassword(HashGenerator.getHashValue(createPatient.getUserAccount().getPassword()));
		//nihar 5 changes
		
		createPatient.getUserAccount().setRole(Role.Patient.toString());
		createPatient.getUserAccount().setPerson(createPatient.getPatient().getPerson());
		createPatient.getUserAccount().getPerson().setHospital(ua.getPerson().getHospital());
		personDao.saveOrUpdate(createPatient.getPatient().getPerson());
		userAccountDao.saveOrUpdate(createPatient.getUserAccount());
		patientDao.saveOrUpdate(createPatient.getPatient());

		return new ModelAndView("admin/home");
	}

}
