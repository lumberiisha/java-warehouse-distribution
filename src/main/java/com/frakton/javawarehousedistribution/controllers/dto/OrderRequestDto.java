package com.frakton.javawarehousedistribution.controllers.dto;

import com.frakton.javawarehousedistribution.controllers.dto.order.OrderItemDto;
import com.frakton.javawarehousedistribution.models.client.OrderItem;

import java.util.List;

public class OrderRequestDto {
    private List<OrderItemDto> orderItems;

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
