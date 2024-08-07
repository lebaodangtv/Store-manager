package com.websitebanhang.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

public class JwtTokenProvider {
    private final SecretKey secretKey;

    // Constructor nhận vào chuỗi Base64 và tạo SecretKey
    public JwtTokenProvider(String base64SecretKey) {
        byte[] keyBytes = Base64.getDecoder().decode(base64SecretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    // Phương thức tạo token JWT
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Phương thức lấy Claims từ token JWT
    public Claims getClaims(String token) {
        JwtParser parser = Jwts.parserBuilder()  // Dùng parserBuilder() để tạo JwtParser
                .setSigningKey(secretKey)
                .build();
        return parser.parseClaimsJws(token).getBody();
    }

    // Phương thức kiểm tra tính hợp lệ của token
    public boolean validateToken(String token) {
        try {
            JwtParser parser = Jwts.parserBuilder()  // Dùng parserBuilder() để tạo JwtParser
                    .setSigningKey(secretKey)
                    .build();
            parser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Getter cho secretKey (nếu cần thiết)
    public SecretKey getSecretKey() {
        return secretKey;
    }
}
