package com.me.src.pojo.command;

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
	
	
}
