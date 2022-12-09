package com.javawarehousedistribution.controllers.dto.warehouse;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import java.util.List;
import java.util.UUID;

public class ListOfProductsRequestDto {

    private List<UUID> listOfProducts;
    public List<UUID> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<UUID> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}
