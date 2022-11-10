package com.frakton.javawarehousedistribution.controllers.dto.order;

import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

public class OrderRequestDto {

    @Size(min = 1,message = "order should have min 1 order item")
    @NotBlank
    private List<UUID> orderItemsId;

    public List<UUID> getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(List<UUID> orderItemsId) {
        this.orderItemsId = orderItemsId;
    }
}
