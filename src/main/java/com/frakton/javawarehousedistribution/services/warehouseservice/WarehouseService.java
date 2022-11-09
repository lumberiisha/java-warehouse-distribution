package com.frakton.javawarehousedistribution.services.warehouseservice;

import com.frakton.javawarehousedistribution.controllers.dto.employee.EmployeeResponseDto;
import com.frakton.javawarehousedistribution.controllers.dto.product.ProductResponseDto;
import com.frakton.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.frakton.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.frakton.javawarehousedistribution.controllers.dto.warehouse.*;
import com.frakton.javawarehousedistribution.models.employee.Employee;
import com.frakton.javawarehousedistribution.models.location.Address;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;
import com.frakton.javawarehousedistribution.repository.warehouse.WarehouseRepository;
import com.frakton.javawarehousedistribution.services.employeeservice.EmployeeService;
import com.frakton.javawarehousedistribution.services.locationservice.AddressService;
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
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final CreateBaseResponse createBaseResponse;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final AddressService addressService;
    ModelMapper modelMapper=new ModelMapper();

    public WarehouseService(WarehouseRepository warehouseRepository, CreateBaseResponse createBaseResponse, EmployeeService employeeService, @Lazy ProductService productService, AddressService addressService) {
        this.warehouseRepository = warehouseRepository;
        this.createBaseResponse = createBaseResponse;
        this.employeeService = employeeService;
        this.productService = productService;
        this.addressService = addressService;
    }
    public ResponseEntity<BaseResponse> createWarehouse(WarehouseRequestDto warehouseRequestDto){
        Warehouse warehouse=modelMapper.map(warehouseRequestDto,Warehouse.class);
        warehouseRepository.save(warehouse);
        return createBaseResponse.createResponse("warehouse created", HttpStatus.CREATED,modelMapper.map(warehouse, WarehouseResponseDto.class));

    }
    public ResponseEntity<BaseResponse> getWarehouses() {
        return createBaseResponse.createResponse("warehouses found",HttpStatus.OK,warehouseRepository.findAll().stream()
                .map(warehouse -> modelMapper.map(warehouse, WarehouseResponseDto.class)).collect(Collectors.toList()));
    }

    public ResponseEntity<BaseResponse> deleteWarehouse(UUID id) {
        Optional<Warehouse> warehouseOptional=warehouseRepository.findById(id);
        if(warehouseOptional.isPresent()){
            Warehouse warehouse=warehouseOptional.get();
            warehouseRepository.delete(warehouse);
            WarehouseResponseDto warehouseResponseDto=modelMapper.map(warehouse,WarehouseResponseDto.class);//todo testo
            return createBaseResponse.createResponse("warehouse deleted",HttpStatus.OK,warehouseResponseDto);
        }else {
            return createBaseResponse.createBadResponse("warehouse not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> updateWarehouse(UUID id, WarehouseRequestDto warehouseRequestDto) {
        return null;
    }

    public Optional<Warehouse> getWarehousesEntity(UUID id) {
        return warehouseRepository.findById(id);
    }

    public ResponseEntity<BaseResponse> getWarehouseById(UUID id) {
        Optional<Warehouse> optionalWarehouse=warehouseRepository.findById(id);
        if(optionalWarehouse.isPresent()){
            Warehouse warehouse=optionalWarehouse.get();
            return createBaseResponse.createResponse("warehouse found",HttpStatus.OK,modelMapper.map(warehouse, WarehouseResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("warehouse not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> setListOfProducts(ListOfProductsRequestDto listOfProductsRequestDto,UUID warehouseId) {
        Optional<Warehouse> warehouseOptional=warehouseRepository.findById(warehouseId);
        if(warehouseOptional.isPresent()){
            Warehouse warehouse=warehouseOptional.get();
            List<UUID> uuidList=listOfProductsRequestDto.getListOfProducts();
            warehouse.getProducts().forEach(p -> {
                uuidList.remove(p.getId());
            });
            List<Product> productList = productService.getProductsInBatch(uuidList);
            List<Product> productListInWarehouse=warehouse.getProducts();
            productListInWarehouse.addAll(productList);
            warehouse.setProducts(productListInWarehouse);
            warehouseRepository.save(warehouse);
            List<ProductResponseDto> productResponseDtoList=productList.stream()
                    .map(product -> modelMapper.map(product, ProductResponseDto.class)).collect(Collectors.toList());
            return createBaseResponse.createResponse("Products added to warehouse",HttpStatus.OK,productResponseDtoList);
        }else {
            return createBaseResponse.createBadResponse("Warehouse Not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> setListOfEmployees(ListOfEmployeesRequestDto listOfEmployeesRequestDto,UUID warehouseId) {
        Optional<Warehouse> optionalWarehouse=warehouseRepository.findById(warehouseId);
        if(optionalWarehouse.isPresent()){
            Warehouse warehouse=optionalWarehouse.get();
            List<Employee> employeeList=employeeService.getEmployeesInBatch(listOfEmployeesRequestDto.getListOfEmploy());
            List<Employee> listOfEmployeeInWarehouse=warehouse.getEmployees();
            listOfEmployeeInWarehouse.addAll(employeeList);
            warehouse.setEmployees(listOfEmployeeInWarehouse);
            warehouseRepository.save(warehouse);
            List<EmployeeResponseDto> employeeResponseDtoList=employeeList.stream()
                    .map(employee -> modelMapper.map(employee, EmployeeResponseDto.class)).collect(Collectors.toList());
            return createBaseResponse.createResponse("Employees added to warehouse",HttpStatus.OK,employeeResponseDtoList);
        }
        else {
            return createBaseResponse.createBadResponse("warehouse not found",HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<BaseResponse> setAddressToWarehouse(UUID warehouseId,UUID addressId) {
        Optional<Warehouse> warehouseOptional=warehouseRepository.findById(warehouseId);
        if(warehouseOptional.isPresent()){
            Warehouse warehouse=warehouseOptional.get();
            Optional<Address> addressOptional=addressService.getAddressEntityById(addressId);
            if(addressOptional.isPresent()){
                Address address=addressOptional.get();
                warehouse.setAddress(address);
                warehouseRepository.save(warehouse);
                return createBaseResponse.createResponse("Address added to warehouse",HttpStatus.OK,modelMapper.map(warehouse, WarehouseResponseDto.class));
            }else {
                return createBaseResponse.createBadResponse("Address not found",HttpStatus.NOT_FOUND);
            }
        }else {
            return createBaseResponse.createBadResponse("warehouse not found",HttpStatus.NOT_FOUND);
        }
    }
}
