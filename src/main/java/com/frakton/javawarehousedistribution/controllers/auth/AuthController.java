package com.frakton.javawarehousedistribution.controllers.auth;

import com.frakton.javawarehousedistribution.controllers.dto.auth.LoginRequestDto;
import com.frakton.javawarehousedistribution.services.authservice.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/api/test")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto){
        return authService.login(requestDto);
    }
}