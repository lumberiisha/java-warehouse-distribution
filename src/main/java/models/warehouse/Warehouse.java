package models.warehouse;

import models.employee.Employee;

import java.util.List;
import java.util.UUID;

public class Warehouse {
    private UUID warehouseId;
    private String warehouseName;
    private List<Product> warehouseProducts;
    private List<Employee> warehouseEmployees;

}
