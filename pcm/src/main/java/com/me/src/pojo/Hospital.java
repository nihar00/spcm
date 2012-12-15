package com.me.src.pojo;

import javax.persistence.Entity;

@Entity
public class Hospital extends MappedModel {
	private String name;
	private String address;
	private String phone;
	//private ArrayList<Patient> patientList;
	//private ArrayList<Doctor> doctorList;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
