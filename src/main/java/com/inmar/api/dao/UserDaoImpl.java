package com.inmar.api.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.inmar.api.model.User;

@Component
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findByUsername(String username) {
		String queryString = "FROM User WHERE username = :username";
		Query<User> query = getSession().createQuery(queryString, User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}

}
