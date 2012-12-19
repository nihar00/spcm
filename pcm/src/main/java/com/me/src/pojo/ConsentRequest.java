package com.me.src.pojo;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class ConsentRequest extends MappedModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Patient patient;
	
	@Type(type="encryptedString")
	private String consentType;
	
	private Integer recordType;
	
	@OneToOne
	private Hospital recordRequester; // entity trying to request medical records
	
	@OneToOne
	private Hospital recordProvider;  // entity who has medical records
	
	@OneToOne
	private UserAccount requestByUser;
	
	@OneToOne
	private UserAccount respondByUser;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getConsentType() {
		return consentType;
	}
	public void setConsentType(String consentType) {
		this.consentType = consentType;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public Hospital getRecordRequester() {
		return recordRequester;
	}
	public void setRecordRequester(Hospital recordRequester) {
		this.recordRequester = recordRequester;
	}
	public Hospital getRecordProvider() {
		return recordProvider;
	}
	public void setRecordProvider(Hospital recordProvider) {
		this.recordProvider = recordProvider;
	}
	public UserAccount getRequestByUser() {
		return requestByUser;
	}
	public void setRequestByUser(UserAccount requestByUser) {
		this.requestByUser = requestByUser;
	}
	public UserAccount getRespondByUser() {
		return respondByUser;
	}
	public void setRespondByUser(UserAccount respondByUser) {
		this.respondByUser = respondByUser;
	}	
	
	
}
