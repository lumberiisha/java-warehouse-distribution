package com.javawarehousedistribution.controllers.dto.order;

import com.javawarehousedistribution.models.client.OrderStatus;

public class OrderStatusRequestDto {
    private OrderStatus status;
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
