//package com.websitebanhang.configuration;
//
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//import javax.crypto.SecretKey;
//import java.util.Base64;
//
//public class GenerateKey {
//    public static void main(String[] args) {
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//        // Mã hóa khóa ký thành định dạng Base64
//        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
//
//        System.out.println("Key (base64 encoded): " + base64Key);
//    }
//}
