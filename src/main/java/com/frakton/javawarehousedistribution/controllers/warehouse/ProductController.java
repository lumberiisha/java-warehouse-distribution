package com.frakton.javawarehousedistribution.controllers.warehouse;

import com.frakton.javawarehousedistribution.controllers.dto.product.ProductDto;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.services.warehouseservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @DeleteMapping("/api/product")
    public Product delete(@RequestBody UUID id){
       return productService.delete(id);
    }
    @PatchMapping("/api/product")
    public Product update(@RequestBody ProductDto productDto){
        return productService.update(productDto);
    }

}
