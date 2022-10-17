package com.frakton.javawarehousedistribution.controllers.auth;

import com.frakton.javawarehousedistribution.controllers.dto.auth.LoginRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.auth.token.TokenDto;
import com.frakton.javawarehousedistribution.services.authservice.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

  private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/api/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto requestDto) throws Exception {
        return authService.login(requestDto);
    }
    @GetMapping("/api/refreshToken")
    public ResponseEntity<TokenDto> refreshToken(HttpServletRequest request){
        return authService.refreshToken(request);
    }
}