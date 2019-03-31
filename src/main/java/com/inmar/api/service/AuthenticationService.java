package com.inmar.api.service;

import java.util.Map;

import com.auth0.jwt.interfaces.Claim;

public interface AuthenticationService {
	public Map<String, Claim> validateToken(String token);

	String generateToken(int userId);
}
