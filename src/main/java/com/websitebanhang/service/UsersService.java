package com.websitebanhang.service;

import java.sql.SQLException;
import java.util.List;

import com.websitebanhang.entitys.Users;

public interface UsersService {
	
	Users doLogin(Users usersRequest);
	Users save(Users users) throws SQLException;
	List<Users> findAll();
	void deleteLogical(String username);
}
