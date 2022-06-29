package com.websitebanhang.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.websitebanhang.entitys.Users;
import com.websitebanhang.repository.UsersRepo;
import com.websitebanhang.service.UsersService;

@Service
public class UserServiceImpl implements UsersService {
	
	private final BCryptPasswordEncoder bcrytPass = new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo usersRepo;

	@Override
	public Users doLogin(Users usersRequest) {
		Users userReponse = usersRepo.findByUsername(usersRequest.getUsername());
		if (ObjectUtils.isNotEmpty(userReponse)) {
			// check pass người dùng nhập vào và pass trong database
			boolean checkPassword = bcrytPass.matches(usersRequest.getHashPassword(), userReponse.getHashPassword());
			return checkPassword ? userReponse : null;
		}
		return null;
	}

}
