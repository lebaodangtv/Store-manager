package com.websitebanhang.service.impl;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.websitebanhang.constant.RolesConstant;
import com.websitebanhang.entitys.Roles;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.repository.UsersRepo;
import com.websitebanhang.service.RolesService;
import com.websitebanhang.service.UsersService;

@Service
public class UserServiceImpl implements UsersService {
	
	private final BCryptPasswordEncoder bcrytPass = new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo usersRepo;
		
	@Autowired
	private RolesService rolesService;

	@Override
	public Users doLogin(Users usersRequest) {
		Users userReponse = usersRepo.findByUsername(usersRequest.getUsername());
		if (ObjectUtils.isNotEmpty(userReponse)) {
			/* check pass người dùng nhập vào và pass trong database */
			boolean checkPassword = bcrytPass.matches(usersRequest.getHashPassword(), userReponse.getHashPassword());
			return checkPassword ? userReponse : null;
		}
		return null;
	}

	@Override
	/* => trong String_Boot khi ko định nghĩ cụ thể thì @Transactional nó mặc định chỉ bắt Error
	 * => kích hoạt cơ chế rollbackon khi có xảy ra lỗi Exception & Error */
	@Transactional(rollbackOn = {Throwable.class})     
	public Users save(Users users) throws SQLException {
		Roles roles = rolesService.findByDescription(RolesConstant.USER);
		users.setRoles(roles);
		/* khi đk thì set false tại tại khoản vừa được khích hoạt không thể là true(delete) */
		users.setIsDeleted(Boolean.FALSE);
		/* mã hóa password gốc trướt khi gán*/
		users.setHashPassword(bcrytPass.encode(users.getHashPassword()));
		/* sao khi thực hiện xong các bước thì gán data vào users*/
		return usersRepo.saveAndFlush(users);
	}

}
