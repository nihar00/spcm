package com.me.src.pojo.command;

import java.util.ArrayList;

public class ConsentCommand {
	
	private String consentType;	
	private ArrayList<String> recordType;

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
