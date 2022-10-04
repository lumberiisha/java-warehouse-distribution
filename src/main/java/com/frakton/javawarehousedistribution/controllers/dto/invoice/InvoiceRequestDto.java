package com.frakton.javawarehousedistribution.controllers.dto.invoice;

import com.frakton.javawarehousedistribution.models.client.InvoiceStatus;

import java.util.Date;
import java.util.UUID;

public class InvoiceRequestDto {
    private UUID id;
    private InvoiceStatus status;
    private Date payDate;
    private UUID orderId;

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

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}
