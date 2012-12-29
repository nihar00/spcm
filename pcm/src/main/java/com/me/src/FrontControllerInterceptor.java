package com.me.src;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.me.src.pojo.UserAccount;
import com.me.src.session.SessionPojo;
import com.me.src.session.SessionService;

public class FrontControllerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	SessionService sessionService;


	private static final Logger logger = LoggerFactory
			.getLogger(FrontControllerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("preHandle()");

		// Session management
		/*for (Cookie cookie : request.getCookies()) {
			logger.info("name of Cookie from request is: "+ cookie.getName());
			logger.info("value of Cookie form request is: "+ cookie.getValue());
		}*/ 

		if(request.getRequestURI().contains("/login.htm") || request.getRequestURI().contains("/logout.htm")){
			return super.preHandle(request, response, handler);
		}

		if(request.getSession()!=null)
		{
			UserAccount user=(UserAccount) request.getSession().getAttribute("userAccount");
			if(user!=null)
			{
				SessionPojo sessionPojo=sessionService.getSessionPojoFromService(user.getUsername());

				//ipcheck
				if(!request.getRemoteAddr().equals(sessionPojo.getIpAddress()))
				{
					logger.info("IpAddress Mismatch" + request.getRemoteAddr() + " "+sessionPojo.getIpAddress());
					response.sendRedirect(request.getContextPath() + "/resources/signout.jsp");
					return false;
				}

				//session Timeout check
				if(sessionPojo.getRecentActivityTime()>120)
				{
					logger.info("Session Timeout" + sessionPojo.getRecentActivityTime());
					request.getSession().invalidate();
					response.sendRedirect(request.getContextPath() + "/resources/signout.jsp");
					return false;
				}

				//step id check
				Cookie c= getStepIdCookie(request);
				
				if(c!=null)
				{
					if(c.getValue().equals(sessionPojo.getStepId())== false)
					{
						response.sendRedirect(request.getContextPath() + "/resources/signout.jsp");
						return false;
					}
				}
			}	
		}

		// Access control management
		if(AccessControlManager.canAccessThisResource(request) == false) {			
			response.sendRedirect(request.getContextPath() + "/resources/unauthorized.jsp");
			return false;
		}

		return super.preHandle(request, response, handler);
	}


	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("postHandle()");

		//Session Management
		if(request.getSession()!=null)
		{
			UserAccount user=(UserAccount) request.getSession().getAttribute("userAccount");
			if(user!=null)
			{
				SessionPojo sessionPojo=sessionService.getSessionPojoFromService(user.getUsername());

				//setting client's ip address in session pojo
				if(request.getHeader("X-FORWARDED-FOR")!=null)
				{
					sessionPojo.setIpAddress(request.getHeader("X-FORWARDED-FOR"));
					logger.info("Client WebProxy Overcome IP Address in Post:" + request.getHeader("X-FORWARDED-FOR"));
				}
				else{
					logger.info("Client Original IP Address in Post:" + request.getRemoteAddr());
					sessionPojo.setIpAddress(request.getRemoteAddr());
				}
				
				//Adding pages visited by the client into session pojo
				sessionPojo.getListOfPageAccessed().add(request.getRequestURI());

				Cookie c= getStepIdCookie(request);
				if(c==null)
				{
					Cookie cookie=new Cookie("stepid", sessionPojo.getNextStepID());
					cookie.setPath("/"); 
					response.addCookie(cookie);
				}
				else
				{
					c.setMaxAge(0);
					Cookie cookie=new Cookie("stepid", sessionPojo.getNextStepID());
					cookie.setPath("/"); 
					response.addCookie(cookie);
					//c.setValue(sessionPojo.getNextStepID());
				}
			}
		}
		//Session Management end

		super.postHandle(request, response, handler, modelAndView);
	}

	private Cookie getStepIdCookie(HttpServletRequest request)
	{
		for (Cookie c : request.getCookies()) {

			if(c.getName().equalsIgnoreCase("stepid"))
			{
				return c;
			}
		}
		return null;
	}

}
