package com.microservice.admin.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String name;
    String username;
    String fullname;
    String hashPassword;
    String email;
    Timestamp createdDate;
    String imgUrl;
    Boolean isDeleted;
    Set<RolesRequest> roles;
}
