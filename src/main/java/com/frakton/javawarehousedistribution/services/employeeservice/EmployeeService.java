package com.frakton.javawarehousedistribution.services.employeeservice;

import com.frakton.javawarehousedistribution.controllers.dto.employee.EmployeeRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.employee.EmployeeResponseDto;
import com.frakton.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.frakton.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.frakton.javawarehousedistribution.models.employee.Employee;
import com.frakton.javawarehousedistribution.models.employee.Vehicle;
import com.frakton.javawarehousedistribution.models.location.Address;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;
import com.frakton.javawarehousedistribution.repository.employee.EmployeeRepository;
import com.frakton.javawarehousedistribution.services.locationservice.AddressService;
import com.frakton.javawarehousedistribution.services.userservice.UserService;
import com.frakton.javawarehousedistribution.services.warehouseservice.WarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CreateBaseResponse createBaseResponse;
    private final AddressService addressService;
    private final WarehouseService warehouseService;
    private final VehicleService vehicleService;
    private final UserService userService;
    private ModelMapper modelMapper=new ModelMapper();


    public EmployeeService(EmployeeRepository employeeRepository, CreateBaseResponse createBaseResponse, AddressService addressService, @Lazy WarehouseService warehouseService, VehicleService vehicleService, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.createBaseResponse = createBaseResponse;
        this.addressService = addressService;
        this.warehouseService = warehouseService;
        this.vehicleService = vehicleService;
        this.userService = userService;
    }

    public ResponseEntity<BaseResponse> getEmployees() {
        return createBaseResponse.createResponse("Employees found", HttpStatus.OK,employeeResponseDtoList(employeeRepository.findAll()));
    }

    public ResponseEntity<BaseResponse> getEmployeesById(UUID id) {
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            return createBaseResponse.createResponse("employee found",HttpStatus.OK,modelMapper.map(employeeOptional.get(), EmployeeResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("employee not found",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<BaseResponse> createEmployee(EmployeeRequestDto employeeRequestDto) {
        User user = userService.createUserEntity(employeeRequestDto.getUserRequestDto());
        Employee employee=modelMapper.map(employeeRequestDto,Employee.class);
        user.setRole(employeeRequestDto.getRole());
        employee.setUser(user);
        employeeRepository.save(employee);
        return createBaseResponse.createResponse("Employee created",HttpStatus.CREATED,modelMapper.map(employee, EmployeeResponseDto.class));
    }
    public List<Employee> getEmployeesInBatch(List<UUID> uuids){
        return employeeRepository.findAllByIdIn(uuids);
    }

    public ResponseEntity<BaseResponse> getEmployeesByWarehouse(UUID id) {
        Optional<Warehouse>warehouseOptional=warehouseService.getWarehousesEntity(id);
        if(warehouseOptional.isPresent()){
            Warehouse warehouse=warehouseOptional.get();
            List<Employee> employeeList=warehouse.getEmployees();
            return createBaseResponse.createResponse("Employees found",HttpStatus.OK,employeeList);
        }else {
            return createBaseResponse.createBadResponse("warehouse not found",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<BaseResponse> setVehicle(UUID employeeId, UUID vehicleId) {
        Optional<Employee>optionalEmployee =employeeRepository.findById(employeeId);
        if(optionalEmployee.isPresent()){
            Employee employee=optionalEmployee.get();
            Optional<Vehicle> optionalVehicle=vehicleService.getVehicleEntityById(vehicleId);
            if(optionalVehicle.isPresent()){
                Vehicle vehicle=optionalVehicle.get();
                employee.setVehicle(vehicle);
                employeeRepository.save(employee);
                return createBaseResponse.createResponse("Vehicle set to employee",HttpStatus.OK,modelMapper.map(employee, EmployeeResponseDto.class));
            }else {
                return createBaseResponse.createBadResponse("Vehicle not Found",HttpStatus.NOT_FOUND);
            }
        }else {
            return createBaseResponse.createBadResponse("Employee not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> setAddress(UUID employeeId,UUID addressId){
        Optional<Employee> optionalEmployee=employeeRepository.findById(employeeId);
        if(optionalEmployee.isPresent()){
            Employee employee=optionalEmployee.get();
            Optional<Address> optionalAddress=addressService.getAddressEntityById(addressId);
            if(optionalAddress.isPresent()){
                Address address=optionalAddress.get();
                employee.setAddress(address);
                employeeRepository.save(employee);
                return createBaseResponse.createResponse("Address set to employee",HttpStatus.OK,modelMapper.map(employee, EmployeeResponseDto.class));
            }else {
                return createBaseResponse.createBadResponse("Address not found",HttpStatus.NOT_FOUND);
            }
        }else {
            return createBaseResponse.createBadResponse("Employee not found",HttpStatus.NOT_FOUND);
        }
    }

    private List<EmployeeResponseDto> employeeResponseDtoList(List<Employee> employeeList){
        return employeeList.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponseDto.class)).collect(Collectors.toList());
    }
}
