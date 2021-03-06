package com.me.src.doctor;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.me.src.dao.ConsentDao;
import com.me.src.dao.ConsentRequestDao;
import com.me.src.dao.HospitalDao;
import com.me.src.dao.MedicalRecordDao;
import com.me.src.dao.PatientDao;
import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Consent;
import com.me.src.pojo.ConsentRequest;
import com.me.src.pojo.Hospital;
import com.me.src.pojo.MedicalRecord;
import com.me.src.pojo.Patient;
import com.me.src.pojo.UserAccount;
import com.me.src.pojo.command.RecordRequestCommand;

@Controller
@RequestMapping("/doctor")
//nihar 4 changes
@SessionAttributes("patient")
//nihar 4 changes
public class RequestRecordController {
	private static final Logger logger = LoggerFactory.getLogger(RequestRecordController.class);

	@Autowired
	ConsentRequestDao consentRequestDao;
	@Autowired
	PatientDao patientDao;
	@Autowired
	UserAccountDao userAccountDao;
	@Autowired
	ConsentDao consentDao;
	@Autowired
	HospitalDao hospitalDao;
	@Autowired
	MedicalRecordDao medicalRecordDao;


	@RequestMapping(value = "/request-record.htm", method = RequestMethod.GET)
	public String initForm(ModelMap model, @RequestParam("patientId") long patientId) {
		RecordRequestCommand request = new RecordRequestCommand();
		//nihar 4 changes
		Patient patient = patientDao.findById(patientId);
		model.addAttribute("hospitalList",hospitalDao.findAll());
		//nihar 4 changes

		model.addAttribute("request", request);

		//nihar 4 changes
		model.addAttribute("patient", patient);
		logger.info("for patient: " + patient.getId());
		//nihar 4 changes

		return "doctor/record-request";
	}

	@RequestMapping(value = "/request-record.htm", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("request") RecordRequestCommand request,@ModelAttribute("patient") Patient patient, BindingResult result,  @RequestParam("hospitalId") long hospitalId,SessionStatus status,HttpSession session,Model model) {		

		ConsentRequest consentRequest = new ConsentRequest();
		consentRequest.setConsentType(request.getConsentType());

		int recordTypes = 0;
		for(String s : request.getRecordType()) {
			int record = Integer.parseInt(s);
			recordTypes |= record;
		}
		consentRequest.setRecordType(recordTypes);

		//nihar 4 changes
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");
		Hospital h=hospitalDao.findById(hospitalId);
		logger.info("Record Request: " + request.getPatientId());
		logger.info("PatientId in consent creation by doctor request: " + patient.getId());
		logger.info( " user: " + ua.getPerson().getFirstName());
		logger.info("Hospital record is requested " + h.getName());


		consentRequest.setPatient(patientDao.findById(request.getPatientId()));
		consentRequest.setRecordProvider(h);
		consentRequest.setRecordRequester(hospitalDao.findById(ua.getPerson().getHospital().getId()));
		consentRequest.setRequestByUser(ua);

		//nihar 4 changes

		consentRequestDao.saveOrUpdate(consentRequest);
		model.addAttribute("patientlist", patientDao.listPatient(ua.getPerson().getHospital().getId()));

		//code for displaying patient details
		model.addAttribute("patient",patientDao.findById(request.getPatientId()));
		Consent consent=consentDao.getConsentFromPatientId(request.getPatientId());
		//logger.info("Consent Type for the Patient is "+ consent.getConsentType());

		if(consent!=null){
			if(consent.getConsentType().equalsIgnoreCase(request.getConsentType()))
			{
				logger.info("Consent Match");
				/*model.addAttribute("medical",medicalRecordDao.listMedicalRecord(request.getPatientId()));*/
				ArrayList<MedicalRecord> record=(ArrayList<MedicalRecord>) medicalRecordDao.listMedicalRecord(request.getPatientId());
				ArrayList<MedicalRecord> temp = new ArrayList<>();
				int consentRecordType=Integer.parseInt(consent.getRecordType());
				
				for (MedicalRecord medicalRecord : record) {
					if((medicalRecord.getRecordType() & (consentRecordType & recordTypes))!=0){
						temp.add(medicalRecord);
						logger.info("Record Type: "+medicalRecord.getRecordType());
						logger.info(" Types: "+consent.getRecordType());
					}
				}
				//model.addAttribute("medical",medicalRecordDao.listMedicalRecord(request.getPatientId(),recordTypes));
				model.addAttribute("medical",temp);
				return "doctor/patient-info";

			}
			else
			{
				logger.info("No Permission");
				return "doctor/home";
			}
			
		}
		else
		{
			logger.info("No Consent Found");
			return "doctor/home";
		}


	}

}
