package com.websitebanhang.api.admin;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private String username;
    private String email;
    public LoginResponse(String token, String username, String email) {
        this.token = token;
        this.username = username;
        this.email = email;
    }
}
