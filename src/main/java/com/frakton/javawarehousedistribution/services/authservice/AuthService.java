package com.frakton.javawarehousedistribution.services.authservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.frakton.javawarehousedistribution.config.securityconfig.MyAuthenticationProvider;
import com.frakton.javawarehousedistribution.controllers.dto.auth.LoginRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.auth.token.TokenDto;
import com.frakton.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.frakton.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthService {
    private final MyAuthenticationProvider myAuthenticationProvider;
    private final CreateBaseResponse createBaseResponse;
    private final UserRepository userRepository;
    private final String secret="testsecret";

    public AuthService(MyAuthenticationProvider myAuthenticationProvider, CreateBaseResponse createBaseResponse, UserRepository userRepository) {
        this.myAuthenticationProvider = myAuthenticationProvider;
        this.createBaseResponse = createBaseResponse;
        this.userRepository = userRepository;
    }

    public ResponseEntity<BaseResponse> login(LoginRequestDto loginRequestDto)  {
        try {
            Authentication authenticationToken = myAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
            TokenDto tokenDto = new TokenDto();
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
            String access_token = JWT.create()
                    .withSubject(authenticationToken.getPrincipal().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    .withClaim("role", authenticationToken.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .sign(algorithm);
            String refresh_token = JWT.create()
                    .withSubject(authenticationToken.getPrincipal().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                    .sign(algorithm);
            tokenDto.setAccess_token(access_token);
            tokenDto.setRefresh_token(refresh_token);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return createBaseResponse.createResponse("User Logged in", HttpStatus.OK, tokenDto);
        }catch (Exception e){
            return createBaseResponse.createBadResponse("Bad Credentials", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> refreshToken(String authorizationHeader) {
        TokenDto tokenDto = new TokenDto();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoded = verifier.verify(refresh_token);
                String username = decoded.getSubject();
                Optional<User> optionalUser = userRepository.findByUserName(username);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    List<String> roleList = new ArrayList<>();
                    roleList.add(user.getRole().toString());
                    String access_token = JWT.create().withSubject(user.getUserName())
                            .withClaim("role", roleList)
                            .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 100))
                            .sign(algorithm);
                    tokenDto.setAccess_token(access_token);
                    tokenDto.setRefresh_token(refresh_token);
                    return createBaseResponse.createResponse("Access token created", HttpStatus.OK, tokenDto);
                }else {
                    return createBaseResponse.createBadResponse("user not present",HttpStatus.NOT_FOUND);
                }
            }catch (Exception e){
                return createBaseResponse.createBadResponse("Bad refresh token",HttpStatus.BAD_REQUEST);
            }
        } else {
            return createBaseResponse.createBadResponse("Refresh token is missing", HttpStatus.NOT_FOUND);
    }
}

    public User getUser(Authentication request) {
        return userRepository.findByUserName(request.getPrincipal().toString()).orElse(null);
    }
}
