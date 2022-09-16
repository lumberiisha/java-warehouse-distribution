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
    private List<Product> products;

    public Order(@JsonProperty("status") OrderStatus status,
                 List<Product> products) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.orderDate = new Date();
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
