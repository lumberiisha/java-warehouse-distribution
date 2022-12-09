package com.javawarehousedistribution.controllers.dto.employee;

import com.javawarehousedistribution.controllers.dto.user.UserRequestDto;
import com.javawarehousedistribution.models.user.Role;

public class EmployeeRequestDto {

    private UserRequestDto userRequestDto;
    private String name;
    private String email;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRequestDto getUserRequestDto() {
        return userRequestDto;
    }

    public void setUserRequestDto(UserRequestDto userRequestDto) {
        this.userRequestDto = userRequestDto;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
