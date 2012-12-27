package com.me.src.pojo.command;

import java.util.ArrayList;

public class RecordRequestCommand {
	private long hospitalId;
	private long patientId;
	private String consentType;	
	private ArrayList<String> recordType;
	
	public long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getConsentType() {
		return consentType;
	}
	public void setConsentType(String consentType) {
		this.consentType = consentType;
	}
	public ArrayList<String> getRecordType() {
		return recordType;
	}
	public void setRecordType(ArrayList<String> recordType) {
		this.recordType = recordType;
	}
	
	
	
}
