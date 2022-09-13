package models.employee;

import lombok.Getter;
import lombok.Setter;
import models.location.Address;
import models.user.User;

import java.util.UUID;
@Getter
@Setter
public class Employee {
    private User user;
    private UUID id;
    private String name;
    private String email;
    private Address address;
    private Vehicle vehicle;
}
