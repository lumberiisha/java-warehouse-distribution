package com.frakton.javawarehousedistribution.controllers.client;

import com.frakton.javawarehousedistribution.controllers.dto.orderItem.OrderItemRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.orderItem.OrderItemResponseDto;
import com.frakton.javawarehousedistribution.services.clientservice.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderItemController {
    @Autowired
    private  OrderItemService orderItemService;
    @GetMapping("/api/orderItem/{id}")
    public ResponseEntity<OrderItemResponseDto> getOderItemById(@PathVariable UUID id){
       return orderItemService.getOrderItemById(id);
    }
    @GetMapping("/api/orderItem")
    public ResponseEntity<List<OrderItemResponseDto>> getOrderItems(){
        return orderItemService.getOrderItems();
    }

    @PostMapping("/api/orderItem")
    public ResponseEntity<OrderItemResponseDto> createOrderItem(@RequestBody OrderItemRequestDto orderItemRequestDto){
        return orderItemService.createOrderItem(orderItemRequestDto);
    }

    @DeleteMapping("/api/orderItem/{id}")
    public ResponseEntity<OrderItemResponseDto> deleteOrderItem(@PathVariable UUID id){
        return orderItemService.deleteOrderItem(id);
    }

    @PutMapping("/api/orderItem/{id}/{quantity}")
    public ResponseEntity<OrderItemResponseDto> updateOrderItem(@PathVariable UUID id, @PathVariable Integer quantity){
        return orderItemService.updateOrderItem(id,quantity);
    }







}
