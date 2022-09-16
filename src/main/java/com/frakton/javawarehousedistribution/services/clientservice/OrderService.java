package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.models.client.OrderStatus;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.services.warehouseservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public static List<Order> ordersDB=new ArrayList<>();
    @Autowired
    public  ProductService productService;

    public void addProductInOrder(Product product){
        productService.addProductInOrder(product);
    }

    public void addOrder(Order order){
        ordersDB.add(new Order(
                OrderStatus.CREATED,
                productService.getOrderedProducts()));
    }

    public List<Order> getOrders(){
        return ordersDB;
    }

}
