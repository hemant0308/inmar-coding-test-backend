package com.inmar.api.service;

import java.util.Map;

import org.springframework.dao.DuplicateKeyException;

public interface UserService {

	public Map<String, Object> loginUser(Map<String, Object> payload);

	public Map<String, Object> registerUser(Map<String, Object> payload) throws DuplicateKeyException;
}
