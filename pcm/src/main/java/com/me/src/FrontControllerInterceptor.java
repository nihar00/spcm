package com.me.src;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FrontControllerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(FrontControllerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		logger.info("preHandle()");		
							
		 //business.getSessionManagementServer().validateSession(request);
		return super.preHandle(request, response, handler);
	}
	

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("postHandle()");
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
