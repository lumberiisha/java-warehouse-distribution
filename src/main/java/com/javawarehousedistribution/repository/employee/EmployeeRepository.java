package com.javawarehousedistribution.repository.employee;

import com.javawarehousedistribution.models.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee findByUserId(UUID id);
    List<Employee> findAllByIdIn(List<UUID> id);
}
