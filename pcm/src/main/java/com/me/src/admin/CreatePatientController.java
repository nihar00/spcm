package com.me.src.admin;

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

import com.me.src.dao.PatientDao;
import com.me.src.dao.PersonDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Role;
import com.me.src.pojo.command.CreatePatient;

@Controller
@RequestMapping("/create-patient.htm")
public class CreatePatientController {
	private static final Logger logger = LoggerFactory.getLogger(CreatePatientController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		CreatePatient createPatient = new CreatePatient();
		model.addAttribute("createPatient", createPatient);
		return "admin/create-patient";
	}

	//nihar changes
	@Autowired
	PatientDao patientDao;
	@Autowired
	PersonDao personDao;
	@Autowired
	UserAccountDao userAccountDao;
	//nihar changes

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("createPatient") CreatePatient createPatient, BindingResult result, SessionStatus status) {		

		logger.info("Patient Name: " +createPatient.getPatient().getPerson().getFirstName());

		//nihar phase 3 changes
		if(userAccountDao.isUsernameExist(createPatient.getUserAccount().getUsername().toLowerCase())) {
			result.rejectValue("userAccount.username","","Username already exist");
			return "admin/create-patient";
		}
		//nihar phase 3 changes

		//nihar changes 
		createPatient.getUserAccount().setRole(Role.Patient.toString());
		createPatient.getUserAccount().setPerson(createPatient.getPatient().getPerson());
		personDao.saveOrUpdate(createPatient.getPatient().getPerson());
		userAccountDao.saveOrUpdate(createPatient.getUserAccount());
		patientDao.saveOrUpdate(createPatient.getPatient());
		//nihar changes

		return "admin/home";
	}

}
