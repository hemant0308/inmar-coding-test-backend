package com.inmar.api.service;

import com.inmar.api.model.Token;

public interface AuthenticationService {
	public Token validateToken(String token);
}
