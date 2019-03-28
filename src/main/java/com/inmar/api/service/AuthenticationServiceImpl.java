package com.inmar.api.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.model.Token;

@Component
@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public Token validateToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
