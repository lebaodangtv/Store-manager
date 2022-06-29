package com.websitebanhang.constant;

public class SessionConstant {
	public static final String CURRENT_USER = "currenUser";
	
	// không cho new hàm tạo 
	// chỉ cho sử dụng qua class không cho new qua đối tượng
	//( tránh trường hợp new lung tung tiêu hao bộ nhớ)
	private SessionConstant() {
		
	}
}
