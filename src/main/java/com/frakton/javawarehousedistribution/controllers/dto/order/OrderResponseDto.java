package com.frakton.javawarehousedistribution.controllers.dto.order;

import com.frakton.javawarehousedistribution.models.client.OrderItem;
import com.frakton.javawarehousedistribution.models.client.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderResponseDto {
    private UUID id;
    private OrderStatus status;
    private Date orderDate;
    private List<OrderItem> orderItems;

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
}
