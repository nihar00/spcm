package com.me.src.pojo.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.ValidationErrorList;
import org.owasp.esapi.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.src.pojo.Doctor;
import com.me.src.pojo.Person;
import com.me.src.pojo.UserAccount;

public class CreateDoctor {
	private Doctor doctor;
	private UserAccount userAccount;

	public CreateDoctor() {
		doctor = new Doctor();
		doctor.setPerson(new Person());		
		userAccount = new UserAccount();
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	// Meena's validation function
    private static final Logger logger = LoggerFactory.getLogger(CreateDoctor.class);
	public ValidationErrorList validate(){
		
		Person person = doctor.getPerson();
		ValidationErrorList errorList = new ValidationErrorList();
		Validator instance = ESAPI.validator();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			
		if ( (instance.getValidInput("username", userAccount.getUsername(), "Name", 255, false, errorList) == null )
		                                    ||
		     (instance.getValidInput("password", userAccount.getPassword(), "Name", 255, false, errorList) == null )
		                                    ||
		     (instance.getValidInput("FirstName", person.getFirstName(), "Name", 255, false, errorList) == null )
						                    ||
		     (instance.getValidInput("LastName", person.getLastName(), "Name", 255, false, errorList) == null )
				                             ||
			 (instance.getValidInput("EmailID", person.getEmailId(), "Email", 255, false, errorList) == null )
					                         ||
			 (instance.getValidInput("Address", person.getAddress(), "Address", 255, false, errorList) == null )
					                         ||
			 (instance.getValidInput("Phone", person.getPhone(), "Phone", 15, false, errorList) == null )
					                        ||
		     (instance.getValidInput("SSN", person.getSsn(), "Name", 15, false, errorList) == null )
				                              ||	     
		     (instance.getValidDate("Dob", df.format(person.getDob()), 
				    		 DateFormat.getDateInstance(DateFormat.SHORT, Locale.US), false, errorList) == null)
   
		   )
		 {
			logger.info("Doctor Validation Failed");
		 }
		 return errorList;
	  }
	
	
}
