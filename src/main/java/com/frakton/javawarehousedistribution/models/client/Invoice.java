package com.frakton.javawarehousedistribution.models.client;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.UUID;
@Entity
public class Invoice {
    @Id
    private UUID id;
    private InvoiceStatus status;
    private Date payDate;
    private Double totalPrice;
    @OneToOne
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

    public Order getOrders() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}