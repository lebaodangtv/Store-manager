package com.websitebanhang.api.admin;

import com.websitebanhang.constant.ApiResponse;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequestMapping("user")
@RestController
public class User {

    @Autowired
    private UsersService usersService;

    @GetMapping("/find")
    public ApiResponse find(){
        return ApiResponse.builder().code(200).data(usersService.findAll()).message("Success").build();
    }

    @PostMapping("/create")
    public ApiResponse create(@RequestBody Users request) throws SQLException {
        return ApiResponse.builder().code(200).data(usersService.save(request)).message("Tạo thành công").build();
    }

}
