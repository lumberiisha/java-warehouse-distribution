package models.employee;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Vehicle {
    @Getter @Setter private UUID vehicleId;
    @Getter @Setter private double vehicleCapacity;
}
