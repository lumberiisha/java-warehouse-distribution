package com.frakton.javawarehousedistribution.controllers.dto.client;

import com.frakton.javawarehousedistribution.controllers.dto.user.UserRequestDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ClientRequestDto {
    @NotNull(message = "User shouldn't be null")
    private UserRequestDto user;
    @NotBlank(message = "Name shouldn't be blank")
    private String name;
    @Pattern(regexp = "\\d+")
    private String phoneNumber;
    @Email(message = "invalid email address")
    private String email;

    public UserRequestDto getUser() {
        return user;
    }

    public void setUser(UserRequestDto user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
