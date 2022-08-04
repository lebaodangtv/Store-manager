package com.websitebanhang.util;

import javax.servlet.http.HttpSession;

import com.websitebanhang.constant.SessionConstant;
import com.websitebanhang.dto.CartDto;
import com.websitebanhang.entitys.Users;

public class SessionUtil {
	private SessionUtil() {}
	
	public static CartDto getCurrentCart(HttpSession session) {
		return (CartDto) session.getAttribute(SessionConstant.CURRENT_CART);
	}
	
	public static Users getCurrentUser(HttpSession session) {
		return (Users) session.getAttribute(SessionConstant.CURRENT_USER);
	}
	
}
