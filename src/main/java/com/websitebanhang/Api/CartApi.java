package com.websitebanhang.Api;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websitebanhang.constant.SessionConstant;
import com.websitebanhang.dto.CartDto;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.CartService;
import com.websitebanhang.util.SessionUtil;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
	
	@Autowired
	private CartService cartService;
	
	// api/cart/update?productId=...&quantity=...&isReplace..
	@GetMapping("/update")
	public ResponseEntity<?> doGetUpdate(@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace") Boolean isREplace,
			HttpSession session){
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		cartService.updateCart(currentCart, productId, quantity, isREplace);
		return ResponseEntity.ok(currentCart);
	}
	
	@GetMapping("/refresh") // gọi api này sao khi remove sp trong giỏ hàng
	public ResponseEntity<?> doGetRefreshData(HttpSession session){
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		return ResponseEntity.ok(currentCart);
	}
	
	// /api/cart/checkout?address=...&phone=...
	@GetMapping("/checkout")
	public ResponseEntity<?> deGetCheckout(@RequestParam("address")
	String address, @RequestParam("phone") String phone,HttpSession session) {
		Users currentUser = SessionUtil.getCurrentUser(session);
		// nếu user chưa đăng nhập ko cho thanh toán
		if(ObjectUtils.isEmpty(currentUser)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //401 tài khoản chưa được xác thực
		}
		// insert
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		try {
			cartService.insert(currentCart, currentUser, address, phone);
			// insert thành công clear giỏ hàng
			session.setAttribute(SessionConstant.CURRENT_CART, new CartDto());
			// return thành công 200
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //400 data sai
		}
	}
	
}
