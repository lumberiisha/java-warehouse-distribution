package com.frakton.javawarehousedistribution.controllers.warehouse;

import com.frakton.javawarehousedistribution.controllers.dto.product.ProductRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.product.ProductResponseDto;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.services.warehouseservice.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/product")
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("api/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable UUID id){
        return productService.getProductById(id);
    }
    @PostMapping("/api/product")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable UUID id){
       return productService.delete(id);
    }

    @PutMapping("api/product/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable UUID id, @RequestBody ProductRequestDto productRequestDto){
       return productService.update(id,productRequestDto);
    }

}
