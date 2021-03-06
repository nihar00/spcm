package com.me.src.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

@Entity
public class Person extends MappedModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Type(type="encryptedString")
	private String firstName;
	
	@Type(type="encryptedString")
	private String lastName;
	
	private boolean gender;
	private Date dob;
	
	@Type(type="encryptedString")
	private String address;
	
	@Type(type="encryptedString")
	private String phone;
	
	@Type(type="encryptedString")
	private String ssn;
	
	@Type(type="encryptedString")
	private String emailId;	
	
	@OneToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.ALL})
	private Hospital hospital;

	public Person() {
		dob = new Date();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}	
	
}
