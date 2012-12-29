package com.me.src.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class SessionPojo {

	private String stepId;
	private long recentActivityTime;
	private String ipAddress;
	private ArrayList<String> listOfPageAccessed;

	@SuppressWarnings("deprecation")
	public SessionPojo() {

		this.recentActivityTime=new Date().getSeconds();
		this.listOfPageAccessed=new ArrayList<>();

	}

	public ArrayList<String> getListOfPageAccessed() {
		return listOfPageAccessed;
	}

	public void setListOfPageAccessed(ArrayList<String> listOfPageAccessed) {
		this.listOfPageAccessed = listOfPageAccessed;
	}

	public long getRecentActivityTime() {
		return recentActivityTime;
	}


	public void setRecentActivityTime() {
		this.recentActivityTime = new Date().getTime();
	}


	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getNextStepID()
	{
		Random randomGenerator = new Random();
		this.stepId=Integer.toString(randomGenerator.nextInt(1002003000));
		return this.stepId;
	}



}
