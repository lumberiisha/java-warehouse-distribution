package com.frakton.javawarehousedistribution.models.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
public class Product {
    private UUID id;
    private String name;
    private double price;
    private Date expirationDate;
    private Date manufacturingDate;
    private String description;

    public Product(@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name,
                   @JsonProperty("price") double price,
                   Date expirationDate, Date manufacturingDate,
                   @JsonProperty("description") String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.expirationDate = new Date();
        this.manufacturingDate = new Date();
        this.description = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
