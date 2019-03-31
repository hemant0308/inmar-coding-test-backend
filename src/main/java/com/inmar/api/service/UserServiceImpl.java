package com.inmar.api.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.dao.UserDao;
import com.inmar.api.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static Logger log = LogManager.getLogger(UserService.class);

	@Autowired
	UserDao userDao;

	@Autowired
	AuthenticationService authenticationService;

	@Override
	public Map<String, Object> loginUser(Map<String, Object> payload) throws BadCredentialsException {
		try {
			String username = (String) payload.get("username");
			String password = (String) payload.get("password");

			User user = userDao.findByUsername(username);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			log.debug("Checking password");
			if (encoder.matches(password, user.getPassword())) {
				log.debug("Password matched.Creating new token");
				String token = authenticationService.generateToken(user.getId());
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("token", token);
				userMap.put("userId", user.getId());
				userMap.put("username", user.getUsername());
				return userMap;
			} else {
				log.debug("Invalid password");
				throw new BadCredentialsException("Invalid credentails");
			}
		} catch (NoResultException e) {
			log.debug("Invalid payload");
			throw new BadCredentialsException("Invalid credentails");
		}

	}

	@Override
	public Map<String, Object> registerUser(Map<String, Object> payload) throws DuplicateKeyException {
		String username = (String) payload.get("username");
		String email = (String) payload.get("email");
		String password = (String) payload.get("password");

		try {
			userDao.findByUsername(username);
			log.debug("Username already exited with : " + username);
			throw new DuplicateKeyException("Username already existed");
		} catch (NoResultException e) {
			log.debug("username is unique.Creating user.");
			User user = new User();
			user.setUsername(username);
			user.setEmail(email);
			log.debug("Generating hash of password");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String hash = encoder.encode(password);
			user.setPassword(hash);
			userDao.saveOrUpdate(user);
			log.debug("Generating new token for user");
			String token = authenticationService.generateToken(user.getId());
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("token", token);
			userMap.put("userId", user.getId());
			userMap.put("username", user.getUsername());
			return userMap;
		}

	}

}
