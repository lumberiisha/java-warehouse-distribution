package com.frakton.javawarehousedistribution.repository.employee;

import com.frakton.javawarehousedistribution.models.employee.Employee;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.services.employeeservice.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee findByUserId(UUID id);
    List<Employee> findAllByIdIn(List<UUID> id);
}
