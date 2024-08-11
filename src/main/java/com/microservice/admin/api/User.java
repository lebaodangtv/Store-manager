package com.microservice.admin.api;

import com.microservice.admin.dto.reponse.IntrospectRespponse;
import com.microservice.admin.dto.reponse.UserRequest;
import com.microservice.admin.dto.request.IntrospectRequest;
import com.microservice.admin.dto.request.LogoutRequest;
import com.microservice.admin.configuration.ConfigJwt;
import com.microservice.admin.constant.ApiResponse;
import com.microservice.admin.dto.request.RefreshRequest;
import com.microservice.admin.entitys.Users;
import com.microservice.admin.service.UsersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RequestMapping("user")
@RestController
@Log4j2
public class User {

    @Autowired
    private UsersService usersService;
    @Autowired
    private ConfigJwt token;

    @GetMapping("/find")
    public ApiResponse find(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(x -> log.info(x.getAuthority()));

        return ApiResponse.builder().code(200).data(usersService.findAll()).message("Success").build();
    }

    @GetMapping("/find/one")
    public ApiResponse findOne(){
        return ApiResponse.builder().code(200).data(token.userRequest()).message("Success").build();
    }

    @PostMapping("/create")
    public ApiResponse create(@RequestBody Users request) throws SQLException {
        return ApiResponse.builder().code(200).data(usersService.save(request)).message("Tạo thành công").build();
    }

    @PostMapping("/update")
    public ApiResponse update(@RequestBody UserRequest request) throws SQLException {
        return ApiResponse.builder().code(200).data(usersService.updateUsers(request)).message("Tạo thành công").build();
    }

    @PostMapping("/introspect-request")
    public IntrospectRespponse introspectRequest(@RequestBody IntrospectRequest request) throws Exception {
        return token.introspectRespponse(request);
    }

    @PostMapping("/logout")
    public ApiResponse logout(@RequestBody LogoutRequest request) throws Exception {
       return ApiResponse.builder().code(200)
               .data(token.logout(request))
               .message("Logout thành công").build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse refreshToken(@RequestBody RefreshRequest request) throws Exception {
        return ApiResponse.builder().code(200)
                .data(token.refreshToken(request))
                .message("refresh thành công").build();
    }

}
