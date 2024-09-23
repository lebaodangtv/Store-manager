package com.websitebanhang.configuration;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.websitebanhang.dto.request.IntrospectRequest;
import com.websitebanhang.dto.reponse.IntrospectRespponse;
import com.websitebanhang.entitys.Users;
import com.websitebanhang.repository.RolesRepo;
import com.websitebanhang.repository.UsersRepo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class GenerateToken {

    @NotNull
    @Value("${jwt.signerKey}")
    private String key;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private RolesRepo rolesRepo;

    /**
     * tạo token có phân quyền
     * @param user
     * @return
     */
    public String generateToken(Users user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512 );
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("dang@gmail.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1,
                                ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload );
        try {
            jwsObject.sign(new MACSigner(key.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("An error occurred: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * lấy ra role của người dùng để tạo quyền truy cập cho token
     * @param users
     * @return
     */
    private String buildScope(Users users){
        StringJoiner stringJoiner = new StringJoiner(" ");

        if(!CollectionUtils.isEmpty(users.getRoles())){
            users.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if(!CollectionUtils.isEmpty(role.getPermission()))
                    role.getPermission().forEach(permission -> {
                        stringJoiner.add(permission.getName());
                    });
            });
        }

        return stringJoiner.toString();
    }

    /**
     * kiểm tra token
     * @param request
     * @return
     * @throws JOSEException
     * @throws ParseException
     */
    public IntrospectRespponse introspectRespponse(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(key.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date exPiTyTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verify = signedJWT.verify(verifier);
        return IntrospectRespponse.builder().valid(verify && exPiTyTime.after(new Date())).build();
    }

    /**
     * Lấy ra username đăng nhập
     */
    public Users userRequest(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        return usersRepo.findByUsername(name);
    }
}
