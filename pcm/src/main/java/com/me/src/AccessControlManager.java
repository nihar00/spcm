package com.me.src;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.src.pojo.Role;
import com.me.src.pojo.UserAccount;

public class AccessControlManager {

	public static boolean canAccessThisResource(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session != null) {
			UserAccount ua = (UserAccount)session.getAttribute("userAccount");
			if(ua != null) {
				if(Role.Admin.toString().equals(ua.getRole())) {
					
				}
			}
		}
		return false;
	}
}
