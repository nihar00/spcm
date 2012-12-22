package com.me.src.patient;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.src.dao.ConsentRequestDao;
import com.me.src.dao.PatientDao;
import com.me.src.pojo.Patient;
import com.me.src.pojo.UserAccount;

@Controller
@RequestMapping("patient/view-consent-history.htm")
public class ViewConsentRequestHistoryController {
	private static final Logger logger = LoggerFactory.getLogger(ViewConsentRequestHistoryController.class);
		
	@Autowired
	ConsentRequestDao consentRequestDao;
	@Autowired
	PatientDao patientDao;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String initForm(ModelMap model) {
//		Consent consent = new Consent();
//		model.addAttribute("consent", consent);
//		return "patient/create-consent";
//	}
	
	//nihar changes added model attribute in function parameter
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewHistory(Model model,HttpSession session) {

		UserAccount ua = (UserAccount)session.getAttribute("userAccount");
		Patient patient=patientDao.getPatientFromPersonId(ua.getPerson().getId());

		logger.info("View Consent History: " + consentRequestDao.findAll().size());
		logger.info("View Patient History: " + patient.getId());
		
		//nihar changes
		model.addAttribute("requests",consentRequestDao.listConsentRequest(patient.getId()));
		//nihar changes
		
		return "patient/request-history";
	}
	
}
