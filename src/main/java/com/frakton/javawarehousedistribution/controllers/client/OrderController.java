package com.frakton.javawarehousedistribution.controllers.client;

import com.frakton.javawarehousedistribution.controllers.dto.OrderRequestDto;
import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.services.clientservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/client/order")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping("/api/order")
    public Order createOrder(@RequestBody OrderRequestDto orderRequest) {
        //TODO change Order to OrderDTO
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/api/order")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}
