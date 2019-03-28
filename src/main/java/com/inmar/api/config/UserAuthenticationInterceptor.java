package com.inmar.api.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

import com.inmar.api.service.AuthenticationService;

@Transactional
public class UserAuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	AuthenticationService authenticationService;

	Logger log = LogManager.getLogger(UserAuthenticationInterceptor.class.getName());

	private Long time;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		return true;/*
					 * String tokenString = request.getHeader("x-access-token"); if (tokenString !=
					 * null && tokenString.length() != 0) { Token token =
					 * authenticationService.validateToken(tokenString); if (token != null) {
					 * log.info("token validations successfull"); request.setAttribute("user",
					 * token.getUser()); return true; } } response.setStatus(401);
					 * log.warn("invalid token"); return false;
					 */
	}

}
