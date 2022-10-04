package com.frakton.javawarehousedistribution.controllers.client;

import com.frakton.javawarehousedistribution.controllers.dto.order.OrderRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.order.OrderResponseDto;
import com.frakton.javawarehousedistribution.services.clientservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
//@RequestMapping("/api/client/order")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @GetMapping("/api/order")
    public ResponseEntity<List<OrderResponseDto>> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping("/api/order/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/api/order")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @DeleteMapping("/api/order/{id}")
    public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable UUID id){
        return orderService.deleteOrder(id);
    }

}
