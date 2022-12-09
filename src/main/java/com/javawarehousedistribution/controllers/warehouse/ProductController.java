package com.javawarehousedistribution.controllers.warehouse;

import com.javawarehousedistribution.controllers.dto.product.ProductRequestDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.services.warehouseservice.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/warehouse/{warehouseId}")
    @PreAuthorize("hasAnyAuthority('CLIENT','OFFICE_WORKER')")
    public ResponseEntity<BaseResponse> getProductsByWarehouse(@PathVariable UUID warehouseId){
        return productService.getProductsByWarehouse(warehouseId);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CLIENT','OFFICE_WORKER')")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable UUID id){
        return productService.getProductById(id);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> getProducts(){
        System.out.println("test");
        return productService.getProducts();
    }
    @PostMapping()
    @PreAuthorize("hasAnyAuthority('OFFICE_WORKER','ADMIN')")
    public ResponseEntity<BaseResponse> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OFFICE_WORKER','ADMIN')")
    public ResponseEntity<BaseResponse> deleteProduct(@PathVariable UUID id){
       return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OFFICE_WORKER','ADMIN')")
    public ResponseEntity<BaseResponse> update(@PathVariable UUID id, @RequestBody ProductRequestDto productRequestDto){
        return productService.updateProduct(id,productRequestDto);
    }

}
