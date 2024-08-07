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

    public LoginResponse(String token) {
        this.token = token;
    }

}
