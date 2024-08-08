package com.websitebanhang.api.admin;

import com.nimbusds.jose.JOSEException;
import com.websitebanhang.configuration.GenerateToken;
import com.websitebanhang.constant.ApiResponse;
import com.websitebanhang.dto.request.IntrospectRequest;
import com.websitebanhang.dto.reponse.IntrospectRespponse;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.UsersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;

@RequestMapping("user")
@RestController
@Log4j2
public class User {

    @Autowired
    private UsersService usersService;
    @Autowired
    private GenerateToken generateToken;

    @GetMapping("/find")
    public ApiResponse find(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(x -> log.info(x.getAuthority()));

        return ApiResponse.builder().code(200).data(usersService.findAll()).message("Success").build();
    }

    @GetMapping("/find/one")
    public ApiResponse findOne(){
        return ApiResponse.builder().code(200).data(generateToken.userRequest()).message("Success").build();
    }

    @PostMapping("/create")
    public ApiResponse create(@RequestBody Users request) throws SQLException {
        return ApiResponse.builder().code(200).data(usersService.save(request)).message("Tạo thành công").build();
    }

    @PostMapping("/introspect-request")
    public IntrospectRespponse introspectRequest(@RequestBody IntrospectRequest request) throws SQLException, ParseException, JOSEException {
        return generateToken.introspectRespponse(request);
    }

}
