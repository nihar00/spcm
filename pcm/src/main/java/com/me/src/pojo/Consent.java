package com.me.src.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Consent extends MappedModel {
	@OneToOne
	private Hospital hospital;
	
	@OneToOne
	private Patient patient; // consent for patient
	private Date date;
	private String consentType;
	private String recordType;	
	
	@OneToOne
	private UserAccount consentCreatedBy; // can be user can patient or proxy (doctor)
	
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getConsentType() {
		return consentType;
	}
	public void setConsentType(String consentType) {
		this.consentType = consentType;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	public UserAccount getConsentCreatedBy() {
		return consentCreatedBy;
	}
	public void setConsentCreatedBy(UserAccount consentCreatedBy) {
		this.consentCreatedBy = consentCreatedBy;
	}
	
	
}
