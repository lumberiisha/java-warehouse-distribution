package com.frakton.javawarehousedistribution.controllers.dto.employee;

import com.frakton.javawarehousedistribution.models.employee.VehicleType;



public class VehicleRequestDto {
    private String plateNumber;
    private Integer personCapacity;
    private VehicleType vehicleType;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getPersonCapacity() {
        return personCapacity;
    }

    public void setPersonCapacity(Integer personCapacity) {
        this.personCapacity = personCapacity;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
