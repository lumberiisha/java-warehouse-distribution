package com.frakton.javawarehousedistribution.controllers.warehouse;

import com.frakton.javawarehousedistribution.controllers.dto.product.ProductRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.frakton.javawarehousedistribution.services.authservice.AuthService;
import com.frakton.javawarehousedistribution.services.warehouseservice.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
