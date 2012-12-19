package com.me.src.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class MedicalRecord extends MappedModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Patient patient;
	@OneToOne
	private Doctor doctor;
	private Date date;
	
	@Type(type="encryptedString")
	private String description;
	
	@Type(type="encryptedString")
	private String prescription;
	
	private Integer recordType; // brain, accident, mental
		
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	
	
	
	
}
