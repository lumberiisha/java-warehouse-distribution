package com.frakton.javawarehousedistribution.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
public class UserAuthorizationFilter  extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login")||request.getServletPath().equals("/api/refreshToken")){
            filterChain.doFilter(request,response);
        }else {
            String authorizationHeader=request.getHeader(AUTHORIZATION);
            if(authorizationHeader!=null&&authorizationHeader.startsWith("Bearer ")){
                try{
                    String token=authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier=JWT.require(algorithm).build();
                    DecodedJWT decodedJWT= verifier.verify(token);
                    String username=decodedJWT.getSubject();
                    String[] roles=decodedJWT.getClaim("role").asArray(String.class);
//                    String role=decodedJWT.getClaim("role").asList(String.class);
                    Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
                    stream(roles).forEach(role->{
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    //Collection<SimpleGrantedAuthority> authorities= stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

                    // authorities.add(new SimpleGrantedAuthority(roles));

                    Authentication authenticationToken=new UsernamePasswordAuthenticationToken(username,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                   // SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username,null ,authorities));
                    filterChain.doFilter(request,response);
                }catch (Exception exception){
                    response.setHeader("error",exception.getMessage());
                    response.sendError(FORBIDDEN.value());
                }

            }else {
                filterChain.doFilter(request,response);
            }
        }
    }
}
