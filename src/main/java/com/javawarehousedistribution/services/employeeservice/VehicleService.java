package com.javawarehousedistribution.services.employeeservice;

import com.javawarehousedistribution.controllers.dto.employee.VehicleRequestDto;
import com.javawarehousedistribution.controllers.dto.employee.VehicleResponseDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.javawarehousedistribution.models.employee.Vehicle;
import com.javawarehousedistribution.repository.employee.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final CreateBaseResponse createBaseResponse;

    private ModelMapper modelMapper=new ModelMapper();

    public VehicleService(VehicleRepository vehicleRepository, CreateBaseResponse createBaseResponse) {
        this.vehicleRepository = vehicleRepository;
        this.createBaseResponse = createBaseResponse;
    }

    public ResponseEntity<BaseResponse> getVehicles() {
        return createBaseResponse.createResponse("Vehicles found", HttpStatus.OK,vehicleRepository.findAll().
                stream().
                map(vehicle -> modelMapper.map(vehicle, VehicleResponseDto.class)).
                collect(Collectors.toList()));
    }

    public ResponseEntity<BaseResponse> getVehicleByID(UUID id) {
        Optional<Vehicle> vehicleOptional=vehicleRepository.findById(id);
        if(vehicleOptional.isPresent()){
            Vehicle vehicle=vehicleOptional.get();
            return createBaseResponse.createResponse("Vehicle found",HttpStatus.OK,modelMapper.map(vehicle,VehicleResponseDto.class));
        }else{
            return createBaseResponse.createBadResponse("Vehicle Not found", HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<BaseResponse> createVehicle(VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle= modelMapper.map(vehicleRequestDto,Vehicle.class);
        vehicleRepository.save(vehicle);
        return createBaseResponse.createResponse("Vehicle created",HttpStatus.OK,modelMapper.map(vehicle,VehicleResponseDto.class));
    }

    public ResponseEntity<BaseResponse> deleteVehicle(UUID id) {
        Optional<Vehicle> vehicleOptional=vehicleRepository.findById(id);
        if(vehicleOptional.isPresent()){
            Vehicle vehicle=vehicleOptional.get();
            vehicleRepository.delete(vehicle);
            return createBaseResponse.createResponse("Vehicle deleted",HttpStatus.OK,modelMapper.map(vehicle,VehicleResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("Vehicle Not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> update(UUID id, VehicleRequestDto vehicleRequestDto) {
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
            return createBaseResponse.createResponse("Vehicle updated",HttpStatus.OK,modelMapper.map(vehicle,VehicleResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("Vehicle Not found",HttpStatus.NOT_FOUND);
        }

    }
    public Optional<Vehicle> getVehicleEntityById(UUID id){
        return vehicleRepository.findById(id);
    }
}
