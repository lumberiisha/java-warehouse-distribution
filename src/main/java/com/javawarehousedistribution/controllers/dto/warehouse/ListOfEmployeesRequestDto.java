package com.javawarehousedistribution.controllers.dto.warehouse;

import java.util.List;
import java.util.UUID;

public class ListOfEmployeesRequestDto {
    private List<UUID> listOfEmploy;

    public List<UUID> getListOfEmploy() {
        return listOfEmploy;
    }

    public void setListOfEmploy(List<UUID> listOfEmploy) {
        this.listOfEmploy = listOfEmploy;
    }
}
