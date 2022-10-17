package com.frakton.javawarehousedistribution.config;

import com.frakton.javawarehousedistribution.config.configservice.MyUserDetailsService;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserManagementConfig {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public MyUserDetailsService userDetailsService(){
        return new MyUserDetailsService(userRepository);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    MyAuthenticationProvider myAuthenticationProvider(){
        return new MyAuthenticationProvider();
    }
}
