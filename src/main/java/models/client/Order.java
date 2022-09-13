package models.client;

import lombok.Getter;
import lombok.Setter;
import models.warehouse.Product;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class Order {
    private UUID id;
    private OrderStatus status;
    private Date orderDate;
    private List<Product> products;
}
