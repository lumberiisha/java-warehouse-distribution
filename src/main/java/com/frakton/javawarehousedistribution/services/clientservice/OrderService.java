package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.controllers.dto.OrderRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.order.OrderItemDto;
import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.models.client.OrderItem;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.services.warehouseservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    public  List<Order> ordersDB=new ArrayList<>();
    @Autowired
    private final ProductService productService;

    public OrderService(ProductService productService) {
        this.productService = productService;
    }


    public List<Order> getOrders(){
        return ordersDB;
    }


    public Order createOrder(OrderRequestDto orderRequest) {
        Order order=new Order();
        order.setId(UUID.randomUUID());
        List<UUID> uuidList= orderRequest.getOrderItems().
                stream().
                map(OrderItemDto::getProductId).
                collect(Collectors.toList());
        List<OrderItem> orderItems = new ArrayList<>();
        List<Product> products = productService.getProductsByIds(uuidList);
        for (OrderItemDto orderItemDto: orderRequest.getOrderItems()){
            Optional<Product> productOptional=products.
                    stream().
                    filter(product -> product.getId().equals(orderItemDto.getProductId())).
                    findFirst();
            OrderItem orderItem=new OrderItem();
            orderItem.setId(UUID.randomUUID());
            Product product=productOptional.get();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItems.add(orderItem);
        }
        //TODO (later) save OrderItems to the repo
        order.setOrderItems(orderItems);
        ordersDB.add(order);
        return order;
    }
}
