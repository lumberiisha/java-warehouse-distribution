package com.javawarehousedistribution.controllers.dto.employee;

import com.javawarehousedistribution.models.employee.Vehicle;
import com.javawarehousedistribution.models.location.Address;
import com.javawarehousedistribution.models.user.User;

import java.util.UUID;

public class EmployeeResponseDto {

    private User user;
    private UUID id;
    private String name;
    private String email;
    private Address address;
    private Vehicle vehicle;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
