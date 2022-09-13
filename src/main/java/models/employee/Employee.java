package models.employee;

import lombok.Getter;
import lombok.Setter;
import models.location.Address;
import models.user.User;

import java.util.UUID;
@Getter
@Setter
public class Employee {
    private User employeeUser;
    private UUID employeeId;
    private String employeeName;
    private String employeeEmail;
    private Address employeeAddress;
    private Vehicle employeeVehicle;
}
