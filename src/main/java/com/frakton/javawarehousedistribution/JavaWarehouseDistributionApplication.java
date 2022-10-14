package com.frakton.javawarehousedistribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class JavaWarehouseDistributionApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWarehouseDistributionApplication.class, args);
    }


}
