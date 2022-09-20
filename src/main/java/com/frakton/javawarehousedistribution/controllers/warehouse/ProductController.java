package com.frakton.javawarehousedistribution.controllers.warehouse;

import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;
import com.frakton.javawarehousedistribution.services.warehouseservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    public ProductService productService;

    @PostMapping("/api/product")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @GetMapping("/api/product")
    public List<Product> getProduct(){
       return productService.getProducts();
    }

//    @PostMapping("/api/product/order")
//    public void addProductInOrder(@RequestBody Product product){
//        productService.addProductInOrder(product);
//    }

//    @GetMapping("/api/product/order")
//    public List<Product> getOrderedProducts(){
//        return productService.getOrderedProducts();
//    }
}
