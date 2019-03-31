package com.inmar.api.service;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.inmar.api.controller.LocationController;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	static Logger log = LogManager.getLogger(LocationController.class);

	@Value("${secret.apiKey}")
	private String apiKey;

	@Override
	public Map<String, Claim> validateToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(apiKey);
		JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
		DecodedJWT jwt = verifier.verify(token);
		log.debug("Token validated successfully");
		return jwt.getClaims();
	}

	@Override
	public String generateToken(int userId) {
		Algorithm algorithm = Algorithm.HMAC256(apiKey);
		String token = JWT.create().withClaim("userId", userId).sign(algorithm);
		log.debug("New Token generated");
		return token;
	}

}
