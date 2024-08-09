package com.websitebanhang.service.impl;

import java.sql.SQLException;
import java.util.List;


import com.websitebanhang.dto.reponse.UserRequest;
import com.websitebanhang.mapper.mapstruct.UserMapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
	private UsersRepo userRepo;
		
	@Autowired
	private RolesService rolesService;

	@Autowired
	private UserMapper userMapper;

	@Override
	public Users doLogin(Users usersRequest) {
		Users userReponse = userRepo.findByUsername(usersRequest.getUsername());
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
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public Users save(Users users) throws SQLException {
		Roles roles = rolesService.findByDescription(RolesConstant.USER);
		//users.setRoles(roles);
		/* khi đk thì set false tại tại khoản vừa được khích hoạt không thể là true(delete) */
		users.setIsDeleted(Boolean.FALSE);
		/* mã hóa password gốc trướt khi gán*/
		users.setHashPassword(bcrytPass.encode(users.getHashPassword()));
		/* sao khi thực hiện xong các bước thì gán data vào users*/
		return userRepo.saveAndFlush(users);
	}
	@Override
	public List<Users> findAll() {
		return userRepo.findByIsDeleted(Boolean.FALSE);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public void deleteLogical(String username) {
		userRepo.deleteLogical(username);
	}

	@Override
	public Users findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public void update(Users user) {
		if(ObjectUtils.isNotEmpty(user) && StringUtils.isEmpty(user.getHashPassword())) {
			userRepo.updateNonPass(user.getEmail(), user.getFullname(), user.getUsername());
		} else {
			String hashPassword = bcrytPass.encode(user.getHashPassword());
			user.setHashPassword(hashPassword);
			userRepo.update(user.getEmail(), hashPassword, user.getFullname(), user.getUsername());
		}
	}

	@Override
	public Object updateUsers(UserRequest request) {
		if(request != null){
			Users users = userMapper.toUser(request);
			if(ObjectUtils.isNotEmpty(users)){
				users = userRepo.save(users);
			}
			return userMapper.toDto(users);
		}
		return null;
	}

}
