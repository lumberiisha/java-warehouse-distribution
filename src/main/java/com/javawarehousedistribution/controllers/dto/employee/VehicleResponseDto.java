package com.javawarehousedistribution.controllers.dto.employee;

import com.javawarehousedistribution.models.employee.VehicleType;

import java.util.UUID;

public class VehicleResponseDto {
    private UUID id;
    private String plateNumber;
    private Integer personCapacity;
    private VehicleType vehicleType;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

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
