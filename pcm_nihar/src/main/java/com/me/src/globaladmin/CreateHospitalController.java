package com.me.src.globaladmin;

import java.util.ArrayList;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.ValidationErrorList;
import org.owasp.esapi.errors.IntrusionException;
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

import com.me.src.dao.HospitalDao;
import com.me.src.dao.PersonDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Role;
import com.me.src.pojo.command.HospitalEnterprise;
import com.me.src.security.HashGenerator;

@Controller
@RequestMapping("/global-admin")
public class CreateHospitalController {
	
	@Autowired
	HospitalDao hospitalDao;
	@Autowired
	UserAccountDao userAccountDao;
	@Autowired
	PersonDao personDao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CreateHospitalController.class);
	
	@RequestMapping(value = "/create-hospital.htm", method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		HospitalEnterprise hospitalEnterprise = new HospitalEnterprise();
		model.addAttribute("hospitalEnterprise", hospitalEnterprise);
		return "global-admin/create-hospital";
	}
	
	@RequestMapping(value = "/create-hospital.htm", method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("hospitalEnterprise") HospitalEnterprise hospitalEnterprise, BindingResult result, SessionStatus status) throws IntrusionException, ValidationException {		
	
		logger.info("Hospital Name" + hospitalEnterprise.getHospital().getName());
		
		//Meena's validation changes
				ValidationErrorList errorList = hospitalEnterprise.validate();
			     if (!errorList.isEmpty()) 
			     {
					ArrayList<String> errorlist = new ArrayList<String>();
					for (Object vo : errorList.errors()) 
					{
				         ValidationException ve = (ValidationException)vo;
				         errorlist.add( ESAPI.encoder().encodeForHTML(ve.getMessage()) );
				         logger.info("Error " + ESAPI.encoder().encodeForHTML(ve.getMessage()));
				    }
					ModelAndView modelAndView = new ModelAndView("global-admin/create-hospital");
				    modelAndView.addObject("errorlist", errorlist);
				    return modelAndView;
				}
			     
		
		if(userAccountDao.isUsernameExist(hospitalEnterprise.getUserAccount().getUsername().toLowerCase())) {
			
			//nihar phase 3 changes
			result.rejectValue("userAccount.username", "", "Username already exist");
			//nihar phase 3 changes
			
			return new ModelAndView("global-admin/create-hospital");
		}
		
		hospitalDao.saveOrUpdate(hospitalEnterprise.getHospital());
		hospitalEnterprise.getUserAccount().getPerson().setHospital(hospitalEnterprise.getHospital());
		personDao.saveOrUpdate(hospitalEnterprise.getUserAccount().getPerson());
		hospitalEnterprise.getUserAccount().setRole(Role.Admin.toString());
		
		//nihar 5 changes
		hospitalEnterprise.getUserAccount().setPassword(HashGenerator.getHashValue(hospitalEnterprise.getUserAccount().getPassword()));
		//nihar 5 changes
		
		userAccountDao.saveOrUpdate(hospitalEnterprise.getUserAccount());

		return new ModelAndView("global-admin/home");
	}
}
