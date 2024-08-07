package com.websitebanhang.api.admin;

import com.nimbusds.jose.JOSEException;
import com.websitebanhang.configuration.GenerateToken;
import com.websitebanhang.constant.ApiResponse;
import com.websitebanhang.dto.IntrospectRequest;
import com.websitebanhang.dto.IntrospectRespponse;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;

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

    @PostMapping("/introspect-request")
    public IntrospectRespponse introspectRequest(@RequestBody IntrospectRequest request) throws SQLException, ParseException, JOSEException {
       var result = GenerateToken.introspectRespponse(request);
       return result;
    }

}
