package com.javawarehousedistribution.controllers.dto.product;


import javax.validation.constraints.NotBlank;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

public class ProductRequestDto {
    @NotBlank(message = "name shouldn't be blank")
    private String name;
    private Double price;
    private String description;
    @NotBlank(message = "expiration date shouldn't be blank")
    private Date expirationDate;
    @NotBlank(message = "manufacturing date shouldn't be blank")
    private Date manufacturingDate;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


