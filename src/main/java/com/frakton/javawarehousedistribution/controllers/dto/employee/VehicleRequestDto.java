package com.frakton.javawarehousedistribution.controllers.dto.employee;

import com.frakton.javawarehousedistribution.models.employee.VehicleType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class VehicleRequestDto {
    @NotBlank
    private String plateNumber;
    @Min(1)
    @Max(5)
    private Integer personCapacity;
    @NotNull
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
