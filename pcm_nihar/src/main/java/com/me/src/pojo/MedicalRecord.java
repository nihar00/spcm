package com.me.src.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.ValidationErrorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
// Meena's validation function
	
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecord.class);
	
    public ValidationErrorList validate(){
		
		ValidationErrorList errorList = new ValidationErrorList();
			
		if ( (ESAPI.validator().getValidInput("Description", description, "Address", 255, false, errorList) == null )
                ||
             (ESAPI.validator().getValidInput("Prescription", prescription, "Address", 255, false, errorList) == null )             
           )
		 {
			logger.info("Medical Record Validation Failed");
		 }
		 return errorList;
	  } 
	
	
	
	
}
