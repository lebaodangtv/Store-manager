package com.websitebanhang.configuration;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.websitebanhang.dto.IntrospectRequest;
import com.websitebanhang.dto.IntrospectRespponse;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class GenerateToken {
//    @Value("${jwt.signerKey}")
    protected static String key = "XX3jXX7J4K2zfBq13azJGXf+zn7VHzQV7cy+IcVNJ1So/cR/9/hh/USAZFeYnkQrZARNQxxvHZvgsPG01oOcsw==";
    public static String generateToken(String username){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512 );
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("dang@gmail.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1,
                                ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customClaim", "Custom")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload );
        try {
            jwsObject.sign(new MACSigner(key));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static IntrospectRespponse introspectRespponse(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(key.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        // kiểm tra hết hạn chưa
        Date exPiTyTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verify = signedJWT.verify(verifier);
        return IntrospectRespponse.builder().valid(verify && exPiTyTime.after(new Date())).build();
    }
}
