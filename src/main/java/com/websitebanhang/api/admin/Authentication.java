package com.websitebanhang.api.admin;

import com.websitebanhang.configuration.GenerateToken;
import com.websitebanhang.constant.ApiResponse;
import com.websitebanhang.dto.request.LoginRequest;
import com.websitebanhang.dto.reponse.LoginResponse;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.impl.CustomUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Log4j2
public class Authentication {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private GenerateToken generateToken;


    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            Users userDetails = customUserDetailsService.users(loginRequest.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ApiResponse.builder().data(LoginResponse.builder()
                    .token(generateToken.generateToken(userDetails))
                    .build()).message("Thành công").code(200).build();
        } catch (Exception e){
            log.info(e);
            return null;
        }
    }
}
