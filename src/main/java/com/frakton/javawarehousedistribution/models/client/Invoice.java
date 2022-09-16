package com.frakton.javawarehousedistribution.models.client;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Invoice {
    private UUID id;
    private InvoiceStatus status;
    private Date payDate;
    private double totalPrice;
    private List<Order> orders;

    public Invoice(InvoiceStatus status,
                   double totalPrice,
                   List<Order> orders) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.payDate = new Date();
        this.totalPrice = totalPrice;
        this.orders = orders;
    }

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}