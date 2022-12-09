package com.javawarehousedistribution.controllers.auth;

import com.javawarehousedistribution.controllers.dto.auth.LoginRequestDto;
import com.javawarehousedistribution.controllers.dto.client.ClientRequestDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.services.authservice.AuthService;
import com.javawarehousedistribution.services.clientservice.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final ClientService clientService;

    public AuthController(AuthService authService, ClientService clientService) {
        this.authService = authService;
        this.clientService = clientService;
    }
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequestDto requestDto) {
      return authService.login(requestDto);
    }
    @GetMapping("/refreshToken")
    public ResponseEntity<BaseResponse> refreshToken(HttpServletRequest request){
        return authService.refreshToken(request.getHeader(AUTHORIZATION));
    }
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> createClient( @RequestBody ClientRequestDto clientRequestDto){
        return clientService.createClient(clientRequestDto);
    }

}