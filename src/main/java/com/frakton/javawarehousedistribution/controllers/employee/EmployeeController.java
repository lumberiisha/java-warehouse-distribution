package com.frakton.javawarehousedistribution.controllers.employee;

import com.frakton.javawarehousedistribution.controllers.dto.employee.EmployeeRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.frakton.javawarehousedistribution.services.employeeservice.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
   private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/warehouse/{id}")
    @PreAuthorize("hasAnyAuthority('OFFICE_WOREKR','ADMIN')")
    public ResponseEntity<BaseResponse>getEmployeesByWarehouse(@PathVariable UUID id){
        return employeeService.getEmployeesByWarehouse(id);
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse>getEmployees(){
        return employeeService.getEmployees();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> getEmployeeBydId(@PathVariable UUID id){
        return employeeService.getEmployeesById(id);
    }
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return employeeService.createEmployee(employeeRequestDto);
    }
    @PatchMapping("/vehicle/{employeeId}/{vehicleId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> setVehicle (@PathVariable UUID employeeId,@PathVariable UUID vehicleId){
        return employeeService.setVehicle(employeeId,vehicleId);
    }
    @PatchMapping("/address/{employeeId}/{addressId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> setAddress (@PathVariable UUID employeeId,@PathVariable UUID addressId){
        return employeeService.setAddress(employeeId,addressId);
    }
}
