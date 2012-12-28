package com.me.src;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.src.pojo.UserAccount;



@Controller
public class LogoutController {
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(HttpSession session,ModelMap model) {
		logger.info("logout nihar");
		session.invalidate();
		UserAccount ua = new UserAccount();
		model.addAttribute("userAccount", ua);
		return "login";
	}
}
