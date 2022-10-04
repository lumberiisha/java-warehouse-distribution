package com.frakton.javawarehousedistribution.controllers.dto.location;

import com.frakton.javawarehousedistribution.models.location.Region;



public class AddressRequestDto {

    private Region region;
    private String street;
    private String city;
    private Integer postalCode;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
