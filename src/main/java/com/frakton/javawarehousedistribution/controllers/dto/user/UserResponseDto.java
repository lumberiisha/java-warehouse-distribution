package com.frakton.javawarehousedistribution.controllers.dto.user;

import com.frakton.javawarehousedistribution.models.user.Role;

import java.util.UUID;

public class UserResponseDto {

    private UUID id;
    private String userName;
    private String password;
    private Role role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
