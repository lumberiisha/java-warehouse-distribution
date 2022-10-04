package com.frakton.javawarehousedistribution.services.employeeservice;

import com.frakton.javawarehousedistribution.controllers.dto.employee.VehicleRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.employee.VehicleResponseDto;
import com.frakton.javawarehousedistribution.models.employee.Vehicle;
import com.frakton.javawarehousedistribution.repository.employee.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private  VehicleRepository vehicleRepository;

    private ModelMapper modelMapper=new ModelMapper();
    public ResponseEntity<List<VehicleResponseDto>> getVehicles() {
       return ResponseEntity.ok(vehicleRepository.findAll().
               stream().
               map(vehicle -> modelMapper.map(vehicle,VehicleResponseDto.class)).
               collect(Collectors.toList()));
    }

    public ResponseEntity<VehicleResponseDto> getVehicleByID(UUID id) {
        Optional<Vehicle> vehicleOptional=vehicleRepository.findById(id);
        if(vehicleOptional.isPresent()){
            Vehicle vehicle=vehicleOptional.get();
            return ResponseEntity.ok(modelMapper.map(vehicle,VehicleResponseDto.class));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<VehicleResponseDto> createVehicle(VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle= modelMapper.map(vehicleRequestDto,Vehicle.class);
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok(modelMapper.map(vehicle,VehicleResponseDto.class));
    }

    public ResponseEntity<VehicleResponseDto> deleteVehicle(UUID id) {
        Optional<Vehicle> vehicleOptional=vehicleRepository.findById(id);
        if(vehicleOptional.isPresent()){
            Vehicle vehicle=vehicleOptional.get();
            vehicleRepository.delete(vehicle);
            return ResponseEntity.ok(modelMapper.map(vehicle,VehicleResponseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<VehicleResponseDto> update(UUID id, VehicleRequestDto vehicleRequestDto) {
        Optional<Vehicle> vehicleOptional=vehicleRepository.findById(id);
        if(vehicleOptional.isPresent()){
            Vehicle vehicle=vehicleOptional.get();
            if(vehicleRequestDto.getVehicleType()!=null){
                vehicle.setVehicleType(vehicleRequestDto.getVehicleType());
            }
            if(vehicleRequestDto.getPlateNumber()!=null){
                vehicle.setPlateNumber(vehicleRequestDto.getPlateNumber());
            }
            if(vehicleRequestDto.getPersonCapacity()!=null){
                vehicle.setPersonCapacity(vehicleRequestDto.getPersonCapacity());
            }
            vehicleRepository.save(vehicle);
            return ResponseEntity.ok(modelMapper.map(vehicle,VehicleResponseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }

    }
}
