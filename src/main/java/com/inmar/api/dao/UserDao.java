package com.inmar.api.dao;

import com.inmar.api.model.User;

public interface UserDao extends BaseDao<User> {

	User findByUsername(String username);

}
