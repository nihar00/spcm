package com.me.src.globaladmin;

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

import com.me.src.dao.HospitalDao;
import com.me.src.dao.PersonDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Role;
import com.me.src.pojo.command.HospitalEnterprise;
import com.me.src.security.HashGenerator;

@Controller
@RequestMapping("global-admin/create-hospital.htm")
public class CreateHospitalController {
	
	@Autowired
	HospitalDao hospitalDao;
	@Autowired
	UserAccountDao userAccountDao;
	@Autowired
	PersonDao personDao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CreateHospitalController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		HospitalEnterprise hospitalEnterprise = new HospitalEnterprise();
		model.addAttribute("hospitalEnterprise", hospitalEnterprise);
		return "global-admin/create-hospital";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("hospitalEnterprise") HospitalEnterprise hospitalEnterprise, BindingResult result, SessionStatus status) {		
	
		logger.info("Hospital Name" + hospitalEnterprise.getHospital().getName());
		
		if(userAccountDao.isUsernameExist(hospitalEnterprise.getUserAccount().getUsername().toLowerCase())) {
			
			//nihar phase 3 changes
			result.rejectValue("userAccount.username", "", "Username already exist");
			//nihar phase 3 changes
			
			return "global-admin/create-hospital";
		}
		
		hospitalDao.saveOrUpdate(hospitalEnterprise.getHospital());
		hospitalEnterprise.getUserAccount().getPerson().setHospital(hospitalEnterprise.getHospital());
		personDao.saveOrUpdate(hospitalEnterprise.getUserAccount().getPerson());
		hospitalEnterprise.getUserAccount().setRole(Role.Admin.toString());
		
		//nihar 5 changes
		hospitalEnterprise.getUserAccount().setPassword(HashGenerator.getHashValue(hospitalEnterprise.getUserAccount().getPassword()));
		//nihar 5 changes
		
		userAccountDao.saveOrUpdate(hospitalEnterprise.getUserAccount());

		return "global-admin/home";
	}
}
