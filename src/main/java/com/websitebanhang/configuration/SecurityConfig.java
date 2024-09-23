package com.websitebanhang.configuration;

import com.websitebanhang.enums.Role;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

/**
 * @EnableMethodSecurity
 * @PreAuthorize("hasAnyRole('ADMIN')")
 * @PostAuthorize("returnObject.username == authentication.name")
 * Xác thực kiểm tra trước khi vào method
 * PreAuthorize trước khi thực hiện method
 * PostAuthorize sau khi thực hiện method kiểm tra quyền
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.signerKey}")
    private String key;
    private final String[] PUBLIC_ENDPOINT_GET = {"/v1/api/products"};
    private final String[] PUBLIC_ENDPOINT_POST = {"/user/create", "/auth/login", "/user/introspect-request"};
    private final String[] PRIVATE_ENDPOINT_ADMIN_GET = {"/user/find"};

    /**
     * .hasAnyAuthority() cấu hình trực tiếp
     * .hasRole() cấu hình linh động
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request ->
                        request.requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINT_GET).permitAll()
                                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT_POST).permitAll()
                                .requestMatchers(HttpMethod.GET,PRIVATE_ENDPOINT_ADMIN_GET)
                                .hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * cấu hình lại ROLE
     * @return
     */
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
