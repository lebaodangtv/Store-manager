package com.websitebanhang.api.admin;

import com.websitebanhang.constant.ApiResponse;
import com.websitebanhang.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class User {

    @Autowired
    private UsersService usersService;

    @GetMapping("/find")
    public ApiResponse find(){
        return ApiResponse.builder().code(200).data(usersService.findAll()).message("Success").build();
    }

}
