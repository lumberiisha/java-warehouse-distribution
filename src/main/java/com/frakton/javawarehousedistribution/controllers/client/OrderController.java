package com.frakton.javawarehousedistribution.controllers.client;

import com.frakton.javawarehousedistribution.models.warehouse.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/client/order")
public class OrderController {
    public static List<Product> productsDB = new ArrayList<>();

    @PostMapping("/")
    public void addProduct(@RequestBody Product product) {
        productsDB.add(new Product(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getExpirationDate(),
                product.getManufacturingDate(),
                product.getDescription()));
    }

    @GetMapping("/")
    public List<Product> getProduct() {
        return productsDB;
    }
}
