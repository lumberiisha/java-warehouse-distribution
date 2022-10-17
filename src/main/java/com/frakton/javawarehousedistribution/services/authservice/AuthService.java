package com.frakton.javawarehousedistribution.services.authservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.frakton.javawarehousedistribution.config.MyAuthenticationProvider;
import com.frakton.javawarehousedistribution.controllers.dto.auth.LoginRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.auth.token.TokenDto;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class AuthService {
    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<TokenDto> login(LoginRequestDto loginRequestDto) throws Exception {
        TokenDto tokenDto = new TokenDto();
      try {
         Authentication authenticationToken =myAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
         if(authenticationToken.isAuthenticated()){

             Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
             String access_token= JWT.create()
                     .withSubject(authenticationToken.getPrincipal().toString())
                     .withExpiresAt(new Date(System.currentTimeMillis()+60*1000))
                     //.withIssuer("/api/login")
                     //.withClaim("role",authenticationToken.getAuthorities().toString())
                     .withClaim("role",authenticationToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                     .sign(algorithm);
             String refresh_token= JWT.create()
                     .withSubject(authenticationToken.getPrincipal().toString())
                     .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                     //.withIssuer("/api/login")
                     .sign(algorithm);
             System.out.println("access_token: "+access_token);
             System.out.println("refresh_token: "+refresh_token);
             tokenDto.setAccess_token(access_token);
             tokenDto.setRefresh_token(refresh_token);

         }
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);

      }catch (BadCredentialsException e){
          throw new Exception("bad credentials");
      }

      return ResponseEntity.ok(tokenDto);

    }

    public ResponseEntity<TokenDto> refreshToken(HttpServletRequest request) {
        String authorizationHeader=request.getHeader(AUTHORIZATION);
        TokenDto tokenDto=new TokenDto();
        if(authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token=authorizationHeader.substring("Bearer ".length()   );
                Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier= JWT.require(algorithm).build();
                DecodedJWT decoded= verifier.verify(refresh_token);
                String username=decoded.getSubject();
                User user=userRepository.findByUserName(username);
                List<String> roleList=new ArrayList<>();
                roleList.add(user.getRole().toString());
                String access_token=JWT.create().withSubject(user.getUserName())
                        .withClaim("role",roleList)
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*100))
                        .sign(algorithm);
                tokenDto.setAccess_token(access_token);
                tokenDto.setRefresh_token(refresh_token);
            }catch (Exception e){
                throw new RuntimeException("Refresh token is not valid");
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
        return ResponseEntity.ok(tokenDto);
    }
}
