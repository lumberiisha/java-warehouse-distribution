package com.frakton.javawarehousedistribution.controllers.dto.order;

import java.util.UUID;

public class OrderItemDto {
    private UUID productId;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}
