package com.me.src.pojo;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;

@Entity
public class Hospital extends MappedModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Type(type="encryptedString")
	private String name;
	@Type(type="encryptedString")
	private String address;
	@Type(type="encryptedString")
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
