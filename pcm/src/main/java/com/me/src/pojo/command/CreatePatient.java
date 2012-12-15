package com.me.src.pojo.command;

import com.me.src.pojo.Patient;
import com.me.src.pojo.Person;
import com.me.src.pojo.UserAccount;

public class CreatePatient {
	private Patient patient;
	private UserAccount userAccount;
	
	public CreatePatient() {
		patient = new Patient();
		patient.setPerson(new Person());
		userAccount = new UserAccount();
	}
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
}
