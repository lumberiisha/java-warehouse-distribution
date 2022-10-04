package com.frakton.javawarehousedistribution.controllers.dto.invoice;

import com.frakton.javawarehousedistribution.models.client.InvoiceStatus;
import com.frakton.javawarehousedistribution.models.client.Order;

import java.util.Date;
import java.util.UUID;

public class InvoiceResponseDto {
    private UUID id;
    private InvoiceStatus status;
    private Date payDate;
    private Double totalPrice;
    private Order order;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
