package com.frakton.javawarehousedistribution.controllers.dto.order;

import com.frakton.javawarehousedistribution.models.client.OrderStatus;

public class OrderStatusRequestDto {
    private OrderStatus status;
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
