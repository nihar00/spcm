package com.me.src.doctor;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.me.src.dao.DoctorDao;
import com.me.src.dao.MedicalRecordDao;
import com.me.src.dao.PatientDao;
import com.me.src.pojo.Doctor;
import com.me.src.pojo.MedicalRecord;
import com.me.src.pojo.Patient;
import com.me.src.pojo.UserAccount;

@Controller
@RequestMapping("/create-medical-record.htm")
public class CreateMedicalRecordController {
	private static final Logger logger = LoggerFactory.getLogger(CreateMedicalRecordController.class);
		
	@Autowired
	PatientDao patientDao;
	@Autowired
	DoctorDao doctorDao;
	@Autowired
	MedicalRecordDao medicalRecordDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model, @RequestParam("patientId") long patientId) {
		
		
		MedicalRecord record = new MedicalRecord();
		Patient patient = patientDao.findById(patientId);		
		//record.setPatient(patient);
		model.addAttribute("record", record);
		model.addAttribute("patient", patient);
		logger.info("for patient: " + patient.getId());
		
		return "doctor/create-medical-record";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("record") MedicalRecord record,@ModelAttribute("patient") Patient patient, BindingResult result, HttpSession session) {		
		
		UserAccount ua = (UserAccount)session.getAttribute("userAccount");
		logger.info("Medical Record: " + record.getDescription() + " " + patient.getId() + " user: " + ua.getPerson().getFirstName());
		
		
		Doctor doctor = doctorDao.findByPersonId(ua.getPerson().getId());
		record.setDoctor(doctor);
		record.setDate(new Date());
		record.setPatient(patient); //already done in GET method
		medicalRecordDao.saveOrUpdate(record);
		
		return "doctor/home";
	}
	
}
