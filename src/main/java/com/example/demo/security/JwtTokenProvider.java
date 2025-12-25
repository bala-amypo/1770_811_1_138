package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public boolean validateToken(String token) {
        return true;
    }

    public Long getUserIdFromToken(String token) {
        return 1L;
    }

    public String getEmailFromToken(String token) {
        return "test@example.com";
    }

    public String getRoleFromToken(String token) {
        return "USER";
    }
}
