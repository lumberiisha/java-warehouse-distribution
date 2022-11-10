package com.frakton.javawarehousedistribution.controllers.dto.auth;

import javax.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank(message = "username must not be blank")
    private String username;
    @NotBlank
    private String password;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
