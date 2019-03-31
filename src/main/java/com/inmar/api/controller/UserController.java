package com.inmar.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inmar.api.config.Mappings;
import com.inmar.api.service.UserService;

@RestController
public class UserController {
	static Logger log = LogManager.getLogger(LocationController.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = Mappings.LOGIN_USER, method = RequestMethod.POST)
	public ResponseEntity<Object> deleteSubCategory(@RequestBody Map<String, Object> payload,
			HttpServletRequest request, HttpServletResponse httpResponse) {
		log.debug("POST Request received for : " + Mappings.LOGIN_USER);

		Map<String, Object> response = new HashMap<String, Object>();

		try {
			Map<String, Object> userMap = userService.loginUser(payload);
			response.put("user", userMap);
			log.debug(userMap.get("username") + " Logged in and generated a new token.");
		} catch (BadCredentialsException e) {
			response.put("message", e.getMessage());
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.REGISTER_USER, method = RequestMethod.POST)
	public ResponseEntity<Object> registerUser(@RequestBody Map<String, Object> payload, HttpServletRequest request,
			HttpServletResponse httpResponse) {
		log.debug("POST Request received for : " + Mappings.REGISTER_USER);

		Map<String, Object> response = new HashMap<String, Object>();

		try {
			Map<String, Object> userMap = userService.registerUser(payload);
			response.put("user", userMap);
			log.debug("New user signed up with username " + userMap.get("username") + " and generated a new token.");

		} catch (DuplicateKeyException e) {
			response.put("message", e.getMessage());
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
