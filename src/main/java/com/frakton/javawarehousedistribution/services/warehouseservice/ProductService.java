package com.frakton.javawarehousedistribution.services.warehouseservice;

import com.frakton.javawarehousedistribution.models.warehouse.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    public static List<Product> productsDB=new ArrayList<>();
    public static List<Product> orderedProducts=new ArrayList<>();

    public void addProduct(Product product){
        productsDB.add(new Product(product.getName(),
                product.getPrice(),
                product.getDescription()));
    }
    public void addProductInOrder(Product product){
        orderedProducts.add(new Product(product.getName(),
                product.getPrice(),
                product.getDescription()));
    }

    public List<Product> getProducts(){
        return productsDB;
    }
    public List<Product> getOrderedProducts(){
        return orderedProducts;
    }

}
