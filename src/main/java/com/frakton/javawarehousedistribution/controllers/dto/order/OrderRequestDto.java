package com.frakton.javawarehousedistribution.controllers.dto.order;

import com.frakton.javawarehousedistribution.models.client.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderRequestDto {

    private UUID id;
    private OrderStatus status;
    private Date orderDate;
    private List<UUID> orderItemsId;

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

    public List<UUID> getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(List<UUID> orderItemsId) {
        this.orderItemsId = orderItemsId;
    }
}
