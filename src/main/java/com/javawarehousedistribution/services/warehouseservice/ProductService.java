package com.javawarehousedistribution.services.warehouseservice;

import com.javawarehousedistribution.controllers.dto.product.ProductRequestDto;
import com.javawarehousedistribution.controllers.dto.product.ProductResponseDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.javawarehousedistribution.models.warehouse.Product;
import com.javawarehousedistribution.models.warehouse.Warehouse;
import com.javawarehousedistribution.repository.warehouse.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CreateBaseResponse createBaseResponse;
    private final WarehouseService warehouseService;
    ModelMapper modelMapper=new ModelMapper();

    public ProductService(ProductRepository productRepository, CreateBaseResponse createBaseResponse, @Lazy WarehouseService warehouseService) {
        this.productRepository = productRepository;
        this.createBaseResponse = createBaseResponse;
        this.warehouseService = warehouseService;
    }

    public ResponseEntity<BaseResponse> createProduct(ProductRequestDto productRequestDto){
        Product product=modelMapper.map(productRequestDto, Product.class);
        productRepository.save(product);
        return createBaseResponse.createResponse("Product Created",HttpStatus.CREATED,modelMapper.map(product, ProductResponseDto.class));
    }

    public ResponseEntity<BaseResponse> getProductsByWarehouse(UUID warehouseId){
        Optional<Warehouse> warehouseOptional=warehouseService.getWarehousesEntity(warehouseId);
        if(warehouseOptional.isPresent()){
            Warehouse warehouse=warehouseOptional.get();
            return createBaseResponse.createResponse("Product found",HttpStatus.OK,productResponseDtoList(warehouse.getProducts()));
        }else {
            return createBaseResponse.createBadResponse("warehouse not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> deleteProduct(UUID id){
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product=productOptional.get();
            productRepository.delete(product);
            return createBaseResponse.createResponse("Product deleted",HttpStatus.OK,modelMapper.map(product, ProductResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("Product Not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> updateProduct(UUID id, ProductRequestDto productRequestDto){
       Optional<Product> optionalProduct = productRepository.findById(id);
       if (optionalProduct.isPresent()){
           Product product = optionalProduct.get();
           if(!productRequestDto.getName().equals(product.getName())){
               product.setName(productRequestDto.getName());
           }
           if(!productRequestDto.getPrice().equals(product.getPrice())){
               product.setPrice(productRequestDto.getPrice());
           }
           if(!productRequestDto.getDescription().equals(product.getDescription())){
               product.setDescription(productRequestDto.getDescription());
           }
           if(!productRequestDto.getExpirationDate().toString().equals(product.getExpirationDate().toString())){
               product.setExpirationDate(productRequestDto.getExpirationDate());
           }
           if(!productRequestDto.getManufacturingDate().toString().equals(product.getManufacturingDate().toString())){
               product.setManufacturingDate(productRequestDto.getManufacturingDate());
           }
           productRepository.save(product);
           return createBaseResponse.createResponse("Product updated",HttpStatus.OK,modelMapper.map(product,ProductResponseDto.class));
       }else {
           return createBaseResponse.createBadResponse("Product Not found",HttpStatus.NOT_FOUND);

       }
    }

    public List<Product> getProductsInBatch(List<UUID> uuids){
        return productRepository.findAllByIdIn(uuids);
    }

    public ResponseEntity<BaseResponse> getProductById(UUID id){
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product=productOptional.get();
            return createBaseResponse.createResponse("Product found",HttpStatus.OK,modelMapper.map(product, ProductResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("Product Not found",HttpStatus.NOT_FOUND);
        }

    }
    public Optional<Product> getProductEntityById(UUID id){
        return productRepository.findById(id);
    }


    public ResponseEntity<BaseResponse> getProducts() {
        return createBaseResponse.createResponse("products found",HttpStatus.OK,productResponseDtoList(productRepository.findAll()));
    }

    public List<ProductResponseDto> productResponseDtoList(List<Product> productList){
        return productList.stream()
                .map(product -> modelMapper.map(product,ProductResponseDto.class))
                .collect(Collectors.toList());
    }
}
