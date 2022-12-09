package com.javawarehousedistribution.controllers.employee;

import com.javawarehousedistribution.controllers.dto.employee.VehicleRequestDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.services.employeeservice.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> getVehicles(){
        return vehicleService.getVehicles();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> getVehicleById(@PathVariable UUID id){
        return vehicleService.getVehicleByID(id);
    }
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> createVehicle(@RequestBody VehicleRequestDto vehicleRequestDto) {
        return vehicleService.createVehicle(vehicleRequestDto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> deleteVehicle(@PathVariable UUID id){
        return vehicleService.deleteVehicle(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> update(@PathVariable UUID id, @RequestBody VehicleRequestDto vehicleRequestDto){
        return vehicleService.update(id,vehicleRequestDto);
    }
}
