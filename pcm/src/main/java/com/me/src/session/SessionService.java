package com.me.src.session;

import java.util.HashMap;

public class SessionService {

	private HashMap<String, SessionPojo> sessionService;

	public SessionService() {
		sessionService=new HashMap<>();
	}

	public HashMap<String, SessionPojo> getSessionService() {
		return sessionService;
	}

	public void addSessionPojoinService(String username,SessionPojo sessionPojo)
	{
		sessionService.put(username, sessionPojo);
	}

	public SessionPojo getSessionPojoFromService(String username)
	{
		SessionPojo sessionPojo=sessionService.get(username);
		return sessionPojo;
	}
	
	public void setSessionService(HashMap<String, SessionPojo> sessionService) {
		this.sessionService = sessionService;
	}

}
