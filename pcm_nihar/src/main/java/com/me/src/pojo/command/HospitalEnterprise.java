package com.me.src.pojo.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.ValidationErrorList;
import org.owasp.esapi.Validator;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.src.pojo.Hospital;
import com.me.src.pojo.Person;
import com.me.src.pojo.UserAccount;

public class HospitalEnterprise {

	private Hospital hospital;
	private UserAccount userAccount;
	
	
	public HospitalEnterprise() {
		hospital = new Hospital();
		userAccount = new UserAccount();
		userAccount.setPerson(new Person());
	}
	
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	// Meena's validation function
		private static final Logger logger = LoggerFactory.getLogger(HospitalEnterprise.class);
		public ValidationErrorList validate() throws IntrusionException, ValidationException{
			
			Person person = userAccount.getPerson();
			ValidationErrorList errorList = new ValidationErrorList();
			Validator instance = ESAPI.validator();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				
			if ( (instance.getValidInput("Name", hospital.getName(), "Name", 255, false, errorList) == null )
				                                ||
			     (instance.getValidInput("Address", hospital.getAddress(), "Address", 1024, false, errorList) == null )
			     								||
				 (instance.getValidInput("AdminPhone", hospital.getPhone(), "Phone", 15, false, errorList) == null ) 
				                                ||
				 (instance.getValidInput("username", userAccount.getUsername(), "Name", 255, false, errorList) == null )
			                                    ||
			     (instance.getValidInput("password", userAccount.getPassword(), "Name", 255, false, errorList) == null )
			                                    ||
			     (instance.getValidInput("FirstName", person.getFirstName(), "Name", 255, false, errorList) == null )
							                    ||
			     (instance.getValidInput("LastName", person.getLastName(), "Name", 255, false, errorList) == null )
					                             ||
				 (instance.getValidInput("EmailID", person.getEmailId(), "Email", 255, false, errorList) == null )
						                         ||
				 (instance.getValidInput("UserAddress", person.getAddress(), "Address", 1024, false, errorList) == null )
						                         ||
				 (instance.getValidInput("personPhone", person.getPhone(), "Phone", 15, false, errorList) == null )
						                        ||
			     (instance.getValidInput("SSN", person.getSsn(), "Name", 15, false, errorList) == null )
					                            ||
				(instance.getValidDate("Dob", df.format(person.getDob()), 
					                   	    		 DateFormat.getDateInstance(DateFormat.SHORT, Locale.US), false) == null)	     
	   
			   )
			 {
				logger.info("Hospital Validation Failed");
			 }
			
			{
				logger.info("Invalid Date");
			}
			 return errorList;
		  }
	
	
}
