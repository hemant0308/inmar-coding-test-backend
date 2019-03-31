package com.inmar.api.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.interfaces.Claim;
import com.inmar.api.service.AuthenticationService;

@Transactional
public class UserAuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	AuthenticationService authenticationService;

	Logger log = LogManager.getLogger(UserAuthenticationInterceptor.class.getName());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String uri = request.getRequestURI();
		log.debug("Requested URI : " + uri);
		if (!uri.equals("/inmar/api/v1" + Mappings.LOGIN_USER)
				&& !uri.equals("/inmar/api/v1" + Mappings.REGISTER_USER)) {
			String token = request.getParameter("access-token");
			if (token != null) {
				try {
					Map<String, Claim> claims = authenticationService.validateToken(token);
					int userId = claims.get("userId").asInt();
					log.debug("User authenticated with userId : " + userId);
					return true;
				} catch (Exception e) {
					log.debug("Request with invalid token");
				}
			} else {
				log.debug("Token not found");
			}
			log.debug("Sending Unauthorized status ..");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		return true;
	}

}
