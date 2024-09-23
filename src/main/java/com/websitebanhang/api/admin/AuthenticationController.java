package com.websitebanhang.api.admin;

import com.websitebanhang.configuration.GenerateToken;
import com.websitebanhang.configuration.JwtTokenProvider;
import com.websitebanhang.constant.ApiResponse;
import com.websitebanhang.dto.LoginRequest;
import com.websitebanhang.dto.LoginResponse;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private GenerateToken generateToken;


    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        Users userDetails = customUserDetailsService.users(loginRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ApiResponse.builder().data(LoginResponse.builder()
                .token(generateToken.generateToken(userDetails))
                .build()).message("Thành công").code(200).build();
    }
}
