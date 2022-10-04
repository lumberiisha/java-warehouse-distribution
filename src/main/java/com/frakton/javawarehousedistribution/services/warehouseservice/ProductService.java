package com.frakton.javawarehousedistribution.services.warehouseservice;

import com.frakton.javawarehousedistribution.controllers.dto.product.ProductRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.product.ProductResponseDto;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.repository.warehouse.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    ModelMapper modelMapper=new ModelMapper();
    public ResponseEntity<ProductResponseDto> createProduct(ProductRequestDto productRequestDto){
        Product product= modelMapper.map(productRequestDto,Product.class);
        productRepository.save(product);
        return ResponseEntity.ok(modelMapper.map(product, ProductResponseDto.class));
    }

    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        return ResponseEntity.ok(productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class)).
                collect(Collectors.toList()));
    }

    public ResponseEntity<ProductResponseDto> delete(UUID id){
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product=productOptional.get();
            productRepository.delete(product);
            return ResponseEntity.ok(modelMapper.map(product, ProductResponseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<ProductResponseDto> update(UUID id, ProductRequestDto productRequestDto){
       Optional<Product> optionalProduct = productRepository.findById(id);
       if (optionalProduct.isPresent()){
           Product product = optionalProduct.get();
           if(productRequestDto.getName()!=null){
               product.setName(productRequestDto.getName());
           }
           if(productRequestDto.getPrice()!=null){
               product.setPrice(productRequestDto.getPrice());
           }
           if(productRequestDto.getDescription()!=null){
               product.setDescription(productRequestDto.getDescription());
           }
           if(productRequestDto.getExpirationDate()!=null){
               product.setExpirationDate((Date) productRequestDto.getExpirationDate());
           }
           if(productRequestDto.getManufacturingDate()!=null){
               product.setManufacturingDate(productRequestDto.getManufacturingDate());
           }
           productRepository.save(product);
           return ResponseEntity.ok(modelMapper.map(product, ProductResponseDto.class));
       }else {
           return ResponseEntity.notFound().build();
       }
    }

    public ResponseEntity<ProductResponseDto> getProductById(UUID id){
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product=productOptional.get();
            return ResponseEntity.ok( modelMapper.map(product, ProductResponseDto.class));
        }else {
           return ResponseEntity.notFound().build();
        }

    }
    public ResponseEntity<Product> getProductEntityById(UUID id){
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.notFound().build();
        }
    }



}
