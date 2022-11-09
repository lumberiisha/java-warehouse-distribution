package com.frakton.javawarehousedistribution.controllers.dto.user;

import com.frakton.javawarehousedistribution.models.user.Role;

import javax.validation.constraints.NotBlank;


public class UserRequestDto {
    @NotBlank(message = "username shouldn't be blank")
    private String userName;
    @NotBlank(message = "password shouldn't be blank")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
