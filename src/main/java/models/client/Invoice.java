package models.client;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class Invoice {
    private UUID id;
    private InvoiceStatus status;
    private Date payDate;
    private List<Order> orders;
}