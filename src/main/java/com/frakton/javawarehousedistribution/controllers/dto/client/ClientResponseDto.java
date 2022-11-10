package com.frakton.javawarehousedistribution.controllers.dto.client;

import com.frakton.javawarehousedistribution.controllers.dto.location.AddressResponseDto;
import com.frakton.javawarehousedistribution.controllers.dto.user.UserResponseDto;
import com.frakton.javawarehousedistribution.models.user.User;

import java.util.UUID;

public class ClientResponseDto {
    private UserResponseDto user;
    private UUID id;
    private String name;
    private String phoneNumber;
    private AddressResponseDto address;
    private String email;

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public AddressResponseDto getAddress() {
        return address;
    }

    public void setAddress(AddressResponseDto address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
