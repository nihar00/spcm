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

import com.me.src.dao.DoctorDao;
import com.me.src.dao.PersonDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Role;
import com.me.src.pojo.UserAccount;
import com.me.src.pojo.command.CreateDoctor;
import com.me.src.security.HashGenerator;
@Controller
@RequestMapping("/admin")
public class CreateDoctorController {
	private static final Logger logger = LoggerFactory.getLogger(CreateDoctorController.class);

	//nihar changes
	@Autowired
	DoctorDao docDao;
	@Autowired
	PersonDao personDao;
	@Autowired
	UserAccountDao userAccountDao;
	//nihar changes

	@RequestMapping(value = "/create-doctor.htm", method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		CreateDoctor createDoctor = new CreateDoctor();
		model.addAttribute("createDoctor", createDoctor);
		return "admin/create-doctor";
	}

	@RequestMapping(value = "/create-doctor.htm", method = RequestMethod.POST)	
	public ModelAndView processSubmit(@ModelAttribute("createDoctor") CreateDoctor createDoctor, BindingResult result, SessionStatus status, HttpSession session) {		

		logger.info("Doctor Name" +createDoctor.getDoctor().getPerson().getFirstName());		
		
		//Meena's validation Changes
		ValidationErrorList errorList = createDoctor.validate();
	     if (!errorList.isEmpty()) 
	     {
			ArrayList<String> errorlist = new ArrayList<String>();
			for (Object vo : errorList.errors()) 
			{
		         ValidationException ve = (ValidationException)vo;
		         errorlist.add( ESAPI.encoder().encodeForHTML(ve.getMessage()) );
		         logger.info("Error " + ESAPI.encoder().encodeForHTML(ve.getMessage()));
		    }
			ModelAndView modelAndView = new ModelAndView("admin/create-doctor");
		    modelAndView.addObject("errorlist", errorlist);
		    return modelAndView;
		}
		if(userAccountDao.isUsernameExist(createDoctor.getUserAccount().getUsername().toLowerCase())) {
			result.rejectValue("userAccount.username","","Username already exist");
			return new ModelAndView("admin/create-doctor");
		}
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");
		
		//nihar 5 changes
		createDoctor.getUserAccount().setPassword(HashGenerator.getHashValue(createDoctor.getUserAccount().getPassword()));
		//nihar 5 changes
		
		createDoctor.getUserAccount().setRole(Role.Doctor.toString());
		createDoctor.getUserAccount().setPerson(createDoctor.getDoctor().getPerson());
		createDoctor.getUserAccount().getPerson().setHospital(ua.getPerson().getHospital());
		personDao.saveOrUpdate(createDoctor.getDoctor().getPerson());
		userAccountDao.saveOrUpdate(createDoctor.getUserAccount());
		docDao.saveOrUpdate(createDoctor.getDoctor());		

		return new ModelAndView("admin/home");
	}
}
