package models.warehouse;

import lombok.Getter;
import lombok.Setter;
import models.employee.Employee;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class Warehouse {
    private UUID warehouseId;
    private String warehouseName;
    private List<Product> warehouseProducts;
    private List<Employee> warehouseEmployees;

}
