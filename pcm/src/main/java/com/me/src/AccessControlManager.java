package com.me.src;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.me.src.pojo.Role;
import com.me.src.pojo.UserAccount;

public class AccessControlManager {

	public static boolean canAccessThisResource(HttpServletRequest request) {
		//System.out.println("URI: " + request.getRequestURI());
		HttpSession session = request.getSession();
		if(session != null) {
			UserAccount ua = (UserAccount)session.getAttribute("userAccount");
			if(ua != null) {
				//System.out.println("Role: " + ua.getRole());				
				
				if(Role.Admin.toString().equals(ua.getRole())) {
					if(request.getRequestURI().contains("/admin/")) {
						return true;
					}
					//else if login go to home page
				}
				else if(Role.Doctor.toString().equals(ua.getRole())) {
					if(request.getRequestURI().contains("/doctor/")) {
						return true;
					}
				}
				else if(Role.Patient.toString().equals(ua.getRole())) {
					if(request.getRequestURI().contains("/patient/")) {
						return true;
					}
				}
				else if(Role.GlobalAdmin.toString().equals(ua.getRole())) {
					if(request.getRequestURI().contains("/global-admin/")) {
						return true;
					}
				}
			}
		}
		
		if(request.getRequestURI().contains("/login.htm") || request.getRequestURI().contains("/logout.htm")) {
			return true;
		}
		
		return false;
	}
}
