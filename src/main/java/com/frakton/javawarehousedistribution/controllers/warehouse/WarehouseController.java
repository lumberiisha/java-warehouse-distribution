package com.frakton.javawarehousedistribution.controllers.warehouse;

import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;
import com.frakton.javawarehousedistribution.services.warehouseservice.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public class WarehouseController {

    @Autowired
    public WarehouseService warehouseService;

    @PostMapping
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse){
       //return warehouseService.addWarehouse(warehouse);
        return null;
    }

    @GetMapping
    public Warehouse findWarehouseById(@RequestBody UUID id){
       // return warehouseService.findWarehouseById(id);
        return null;
    }
}
