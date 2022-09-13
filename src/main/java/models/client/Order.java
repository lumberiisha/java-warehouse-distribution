package models.client;

import models.warehouse.Product;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private OrderStatus orderStatus;
    private Date orderDate;
    private List<Product> orderedProducts;
}
