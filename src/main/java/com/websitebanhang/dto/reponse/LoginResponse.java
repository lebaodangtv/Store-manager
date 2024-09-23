package com.websitebanhang.dto.reponse;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    private String token;
    private String username;
    private String email;
    private String fullName;
}
