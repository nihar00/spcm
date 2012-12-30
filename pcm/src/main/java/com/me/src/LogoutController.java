package com.me.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.src.pojo.UserAccount;
import com.me.src.session.SessionPojo;
import com.me.src.session.SessionService;



@Controller
public class LogoutController {
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);
	@Autowired
	SessionService sessionService;

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(HttpSession session,ModelMap model,HttpServletRequest request) {
		logger.info("logout nihar");
		printPagesAccessedByClient(request);
		Cookie c=getStepIdCookie(request);
		c.setMaxAge(0);
		session.invalidate();
		UserAccount ua = new UserAccount();
		model.addAttribute("userAccount", ua);
		return "login";
	}

	private void printPagesAccessedByClient(HttpServletRequest request) {
		UserAccount user=(UserAccount) request.getSession().getAttribute("userAccount");
		SessionPojo sessionPojo=sessionService.getSessionPojoFromService(user.getUsername());
		//file handling
		File file =new File("Record.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(file.getName(),true);
			int i=1;
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(System.getProperty( "line.separator" )+"Username :"+user.getUsername());
			for (String s : sessionPojo.getListOfPageAccessed()) {
				logger.info("Page Accessed: " + s);
				bufferWritter.write(System.getProperty( "line.separator" )+"Step: "+ i+System.getProperty( "line.separator" )+s);
				i++;
			}
			bufferWritter.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Cookie getStepIdCookie(HttpServletRequest request)
	{
		for (Cookie c : request.getCookies()) {

			if(c.getName().equalsIgnoreCase("stepid"))
			{
				return c;
			}
		}
		return null;
	}
}
