package com.frakton.javawarehousedistribution.models.employee;

import com.frakton.javawarehousedistribution.models.location.Address;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;

import javax.persistence.*;
import java.util.UUID;
@Entity
public class  Employee {
    @OneToOne
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    @OneToOne
    private Address address;
    @OneToOne
    private Vehicle vehicle;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
