package com.websitebanhang.util;

import javax.servlet.http.HttpSession;

import com.websitebanhang.constant.SessionConstant;
import com.websitebanhang.dto.CartDto;

public class SessionUtil {
	private SessionUtil() {}
	
	public static CartDto getCurrentCart(HttpSession session) {
		return (CartDto) session.getAttribute(SessionConstant.CURRENT_CART);
	}
}
