package com.frakton.javawarehousedistribution.controllers.employee;

import com.frakton.javawarehousedistribution.controllers.dto.employee.VehicleRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.employee.VehicleResponseDto;
import com.frakton.javawarehousedistribution.models.employee.Vehicle;
import com.frakton.javawarehousedistribution.services.employeeservice.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class VehicleController {
    public final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/api/vehicle")
    public ResponseEntity<List<VehicleResponseDto>> getVehicles(){
        return vehicleService.getVehicles();
    }
    @GetMapping("/api/vehicle/{id}")
    public ResponseEntity<VehicleResponseDto> getVehicleById(@PathVariable UUID id){
        return vehicleService.getVehicleByID(id);
    }
    @PostMapping("/api/vehicle")
    public ResponseEntity<VehicleResponseDto> createVehicle(@RequestBody VehicleRequestDto vehicleRequestDto) {
        return vehicleService.createVehicle(vehicleRequestDto);
    }

    @DeleteMapping("/api/vehicle/{id}")
    public ResponseEntity<VehicleResponseDto> deleteVehicle(@PathVariable UUID id){
        return vehicleService.deleteVehicle(id);
    }

    @PutMapping("/api/vehicle/{id}")
    public ResponseEntity<VehicleResponseDto> update(@PathVariable UUID id, @RequestBody VehicleRequestDto vehicleRequestDto){
        return vehicleService.update(id,vehicleRequestDto);
    }

}
