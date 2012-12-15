package com.me.src;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class LogoutController {
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout() {
		logger.info("logout");
		
		return "admin/home";
	}
}
