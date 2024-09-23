package com.websitebanhang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.websitebanhang.constant.SessionConstant;
import com.websitebanhang.dto.CartDto;

@Component
public class SessionFilter implements Filter {

	/*
	 * servletRequest không có session trong đó phải parst sang httpServletRequest
	 * để sài session
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// lấy ra session
		HttpSession session = httpRequest.getSession();
		validateCart(session);
		chain.doFilter(request, response);
	}

	private void validateCart(HttpSession session) {
		// kiểm tra trong session có giỏ hàng chưa
		if (ObjectUtils.isEmpty(session.getAttribute(SessionConstant.CURRENT_CART))) {
			session.setAttribute(SessionConstant.CURRENT_CART, new CartDto());
		}
	}

}
