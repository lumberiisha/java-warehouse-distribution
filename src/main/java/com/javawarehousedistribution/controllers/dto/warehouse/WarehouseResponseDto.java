package com.javawarehousedistribution.controllers.dto.warehouse;

import com.javawarehousedistribution.models.location.Address;

import java.util.UUID;

public class WarehouseResponseDto {
    private UUID id;
    private String name;
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
