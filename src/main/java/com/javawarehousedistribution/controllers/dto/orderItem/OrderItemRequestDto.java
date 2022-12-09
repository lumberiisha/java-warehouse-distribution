package com.javawarehousedistribution.controllers.dto.orderItem;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class OrderItemRequestDto {
    @NotNull(message = "product shouldn't be null")
    private UUID productId;
    @Min(1)
    private Integer quantity;
    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
