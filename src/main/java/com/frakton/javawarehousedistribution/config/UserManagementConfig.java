package com.frakton.javawarehousedistribution.config;

import com.frakton.javawarehousedistribution.config.configservice.MyUserDetailsService;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserManagementConfig {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new MyAuthenticationProvider();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService(userRepository);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
