package com.frakton.javawarehousedistribution.controllers.warehouse;

import com.frakton.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.frakton.javawarehousedistribution.controllers.dto.warehouse.ListOfEmployeesRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.warehouse.ListOfProductsRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.warehouse.WarehouseRequestDto;
import com.frakton.javawarehousedistribution.services.warehouseservice.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> createWarehouse(@RequestBody WarehouseRequestDto warehouseRequestDto){
       return warehouseService.createWarehouse(warehouseRequestDto);
    }
    @PatchMapping("/products/{warehouseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','OFFICE_WORKER')")
    public ResponseEntity<BaseResponse> setListOfProducts(@RequestBody ListOfProductsRequestDto listOfProductsRequestDto,@PathVariable UUID warehouseId){
        return warehouseService.setListOfProducts(listOfProductsRequestDto,warehouseId);
    }
    @PatchMapping("/employees/{warehouseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> setListOfEmployees(@RequestBody ListOfEmployeesRequestDto listOfEmployeesRequestDto,@PathVariable UUID warehouseId){
        return warehouseService.setListOfEmployees(listOfEmployeesRequestDto,warehouseId);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CLIENT','OFFICE_WORKER','ADMIN')")
    public ResponseEntity<BaseResponse> getWarehouseById (@PathVariable UUID id){
        return warehouseService.getWarehouseById(id);
    }
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('CLIENT','ADMIN')")
    public ResponseEntity<BaseResponse> getWarehouses(){
        return warehouseService.getWarehouses();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> deleteWarehouse(@PathVariable UUID id){
        return warehouseService.deleteWarehouse(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> updateWarehouse(@PathVariable UUID id,@RequestBody WarehouseRequestDto warehouseRequestDto){
        return warehouseService.updateWarehouse(id,warehouseRequestDto);
    }

    @PatchMapping("/{warehouseId}/{addressId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> setAddressToWarehouse(@PathVariable UUID warehouseId,@PathVariable UUID addressId){
        return warehouseService.setAddressToWarehouse(warehouseId,addressId);
    }

}
