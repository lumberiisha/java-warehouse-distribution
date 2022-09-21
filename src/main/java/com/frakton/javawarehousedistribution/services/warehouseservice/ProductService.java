package com.frakton.javawarehousedistribution.services.warehouseservice;

import com.frakton.javawarehousedistribution.controllers.dto.product.ProductDto;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

@Service
public class ProductService {
    public final List<Product> productsDB=new ArrayList<>();
    public void addProduct(Product product){
        product.setId(UUID.randomUUID());
        productsDB.add(product);
    }
    public Product delete(UUID id){
        Product product=getProductByID(id);
        productsDB.remove(product);
        return product;
    }
    public Product update(ProductDto productDto){
        Product product=getProductByID(productDto.getId());
        if(!productDto.getName().isEmpty()){product.setName(productDto.getName());}
        if(!productDto.getName().isEmpty()){product.setPrice(productDto.getPrice());}
        if(!productDto.getName().isEmpty()){product.setDescription(productDto.getDescription());}
        return product;
    }

    public Product getProductByID(UUID id){
       return productsDB.stream().filter(product -> product.getId().equals(id)).findFirst().get();
    }

    public List<Product> getProducts(){
        return productsDB;
    }


    public List<Product> getProductsByIds(List<UUID> productIds){
        List<Product> products=new ArrayList<>();
        for (UUID id :productIds) {
            for (Product product :productsDB) {
                if(id.equals(product.getId())){
                    products.add(product);
                }
            }
        }
        //TODO
//        for (UUID id :productIds) {
//            products.add(productsDB.stream().filter(product -> product.getId().equals(id)).findFirst().get())
//        }
        return products;
    }

}
