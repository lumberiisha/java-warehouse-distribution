package com.frakton.javawarehousedistribution.controllers.dto.orderItem;

import com.frakton.javawarehousedistribution.models.warehouse.Product;

import java.util.UUID;

public class OrderItemResponseDto {
    private UUID id;
    private Product product;
    private Integer quantity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
