package models.client;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Invoice {
    private UUID invoiceId;
    private InvoiceStatus invoiceStatus;
    private Date invoicePayDate;
    private List<Order> invoiceOrders;
}
