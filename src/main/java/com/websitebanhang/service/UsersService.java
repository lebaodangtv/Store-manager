package com.websitebanhang.service;

import java.sql.SQLException;
import java.util.List;

import com.websitebanhang.entitys.Users;

public interface UsersService {
	
	Users doLogin(Users usersRequest);
	Users save(Users users) throws SQLException;
	List<Users> findAll();
	Users findByUsername(String username);
	void deleteLogical(String username);
	void update(Users user);
}
