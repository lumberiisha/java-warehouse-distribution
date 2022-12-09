package com.javawarehousedistribution.controllers.dto.location;

import com.javawarehousedistribution.models.location.Region;

import javax.validation.constraints.*;


public class AddressRequestDto {
    @NotNull
    private Region region;
    @NotBlank(message = "street shouldn't be blank")
    private String street;
    @NotBlank(message = "city shouldn't be blank")
    private String city;
    @Pattern(regexp = "\\d+",message = "invalid postal code")
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
