package com.me.src.admin;

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

import com.me.src.dao.DoctorDao;
import com.me.src.dao.PersonDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Role;
import com.me.src.pojo.UserAccount;
import com.me.src.pojo.command.CreateDoctor;
import com.me.src.security.HashGenerator;
@Controller
@RequestMapping("/create-doctor.htm")

public class CreateDoctorController {
	private static final Logger logger = LoggerFactory.getLogger(CreateDoctorController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		CreateDoctor createDoctor = new CreateDoctor();
		model.addAttribute("createDoctor", createDoctor);
		return "admin/create-doctor";
	}

	//nihar changes
	@Autowired
	DoctorDao docDao;
	@Autowired
	PersonDao personDao;
	@Autowired
	UserAccountDao userAccountDao;
	//nihar changes

	@RequestMapping(method = RequestMethod.POST)	
	public String processSubmit(@ModelAttribute("createDoctor") CreateDoctor createDoctor, BindingResult result, SessionStatus status, HttpSession session) {		

		logger.info("Doctor Name" +createDoctor.getDoctor().getPerson().getFirstName());		
		
		if(userAccountDao.isUsernameExist(createDoctor.getUserAccount().getUsername().toLowerCase())) {
			result.rejectValue("userAccount.username","","Username already exist");
			return "admin/create-doctor";
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

		return "admin/home";
	}

}
