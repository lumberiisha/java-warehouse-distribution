package com.frakton.javawarehousedistribution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class SecurityConfig  {


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//       http.formLogin();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/api/test").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
        http.addFilterBefore(new AuthenticationFilter(), BasicAuthenticationFilter.class);
        return http.build();
   }
}
