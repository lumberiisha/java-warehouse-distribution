package com.frakton.javawarehousedistribution.models.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frakton.javawarehousedistribution.models.warehouse.Product;

import java.util.Date;
import java.util.List;
import java.util.UUID;
public class Order {
    private UUID id;
    private OrderStatus status;
    private Date orderDate;
    private List<OrderItem> orderItems;

    public Order() {
        this.id = UUID.randomUUID();
//        this.status = status;
        this.orderDate = new Date();
//        this.products = products;
    }
    public Order(List<OrderItem> orderItems){
        this();
        this.orderItems=orderItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
}
