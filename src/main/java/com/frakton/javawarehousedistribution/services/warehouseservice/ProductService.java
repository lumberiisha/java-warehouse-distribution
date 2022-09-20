package com.frakton.javawarehousedistribution.services.warehouseservice;

import com.frakton.javawarehousedistribution.models.warehouse.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

@Service
public class ProductService {
    public final List<Product> productsDB=new ArrayList<>();
    public void addProduct(Product product){
        productsDB.add(product);
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
        return products;
    }

}
