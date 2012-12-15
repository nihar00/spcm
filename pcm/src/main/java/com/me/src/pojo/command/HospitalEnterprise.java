package com.me.src.pojo.command;

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
	
	
}
