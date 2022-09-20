package com.frakton.javawarehousedistribution.controllers.warehouse;

import com.frakton.javawarehousedistribution.models.warehouse.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class WarehouseController {

    @PostMapping
    public void addProduct(Product product){
        System.out.println("Office worker add products");
    }

    @GetMapping
    public void getProducts(){
        System.out.println("return products on warehouse");
    }
}
