package models.client;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class Invoice {
    private UUID invoiceId;
    private InvoiceStatus invoiceStatus;
    private Date invoicePayDate;
    private List<Order> invoiceOrders;
}