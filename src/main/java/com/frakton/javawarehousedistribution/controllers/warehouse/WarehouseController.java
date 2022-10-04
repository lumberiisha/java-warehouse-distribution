package com.frakton.javawarehousedistribution.controllers.warehouse;

import com.frakton.javawarehousedistribution.models.location.Address;
import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;
import com.frakton.javawarehousedistribution.services.warehouseservice.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
@RestController
public class WarehouseController {


    @Autowired
    public  WarehouseService warehouseService;


    @PostMapping("/api/warehouse")
    public Optional<Warehouse> addWarehouse(@RequestBody Warehouse warehouse){
       return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping
    public Warehouse findWarehouseById(@RequestBody UUID id){
       // return warehouseService.findWarehouseById(id);
        return null;
    }

}
