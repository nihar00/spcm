package com.me.src.pojo;

import java.util.Date;

import javax.crypto.SealedObject;

public class SessionPojo {

	private byte[] sessionId;
	private byte[] stepId;
	private Date sessionCreationDate;
	private SealedObject data;
	
	public byte[] getSessionId() {
		return sessionId;
	}
	public void setSessionId(byte[] sessionId) {
		this.sessionId = sessionId;
	}
	public byte[] getStepId() {
		return stepId;
	}
	public void setStepId(byte[] stepId) {
		this.stepId = stepId;
	}
	public Date getSessionCreationDate() {
		return sessionCreationDate;
	}
	public void setSessionCreationDate(Date sessionCreationDate) {
		this.sessionCreationDate = sessionCreationDate;
	}
	public SealedObject getData() {
		return data;
	}
	public void setData(SealedObject data) {
		this.data = data;
	}
	
	
	
}
