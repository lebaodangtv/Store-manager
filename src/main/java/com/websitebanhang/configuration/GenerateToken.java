package com.websitebanhang.configuration;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.websitebanhang.dto.IntrospectRequest;
import com.websitebanhang.dto.IntrospectRespponse;
import com.websitebanhang.entitys.Users;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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

    private String buildScope(Users users){
        String roles = "";
        if(users.getRoles() != null){
            roles = users.getRoles().getDescription();
        }
        return roles;
    }

    public IntrospectRespponse introspectRespponse(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(key.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date exPiTyTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verify = signedJWT.verify(verifier);
        return IntrospectRespponse.builder().valid(verify && exPiTyTime.after(new Date())).build();
    }
}
