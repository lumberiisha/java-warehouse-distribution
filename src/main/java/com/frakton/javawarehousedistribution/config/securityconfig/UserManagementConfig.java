package com.frakton.javawarehousedistribution.config.securityconfig;

import com.frakton.javawarehousedistribution.config.securityconfig.configservice.MyUserDetailsService;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserManagementConfig {
    private final UserRepository userRepository;

    public UserManagementConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Bean
    public MyUserDetailsService userDetailsService(){
        return new MyUserDetailsService(userRepository);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MyAuthenticationProvider myAuthenticationProvider(MyUserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
        return new MyAuthenticationProvider(userDetailsService, passwordEncoder);
    }
}
