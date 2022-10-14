package com.frakton.javawarehousedistribution.services.authservice;

import com.frakton.javawarehousedistribution.config.MyAuthenticationProvider;
import com.frakton.javawarehousedistribution.controllers.dto.auth.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    MyAuthenticationProvider myAuthenticationProvider;
    public ResponseEntity<String> login(LoginRequestDto requestDto) {
        SecurityContextHolder.getContext().setAuthentication(myAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(),requestDto.getPassword())));
        return ResponseEntity.ok("test");
    }
}
