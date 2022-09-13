package models.warehouse;

import lombok.Getter;
import lombok.Setter;
import models.employee.Employee;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class Warehouse {
    private UUID id;
    private String name;
    private List<Product> products;
    private List<Employee> employees;
}
