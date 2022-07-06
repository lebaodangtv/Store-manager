package com.websitebanhang.service;

import java.sql.SQLException;

import com.websitebanhang.entitys.Users;

public interface UsersService {
	Users doLogin(Users usersRequest);
	Users save(Users users) throws SQLException;
}
