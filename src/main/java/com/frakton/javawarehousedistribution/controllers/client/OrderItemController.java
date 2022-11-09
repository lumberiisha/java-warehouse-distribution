package com.frakton.javawarehousedistribution.controllers.client;

import com.frakton.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.frakton.javawarehousedistribution.controllers.dto.orderItem.OrderItemRequestDto;
import com.frakton.javawarehousedistribution.services.clientservice.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")//todo rolet
    public ResponseEntity<BaseResponse> getOderItemById(@PathVariable UUID id){
        return orderItemService.getOrderItemById(id);
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('CLIENT')")//todo rolet//todo nashta sna vyne
    public ResponseEntity<BaseResponse> getOrderItems(){
        return orderItemService.getOrderItems();
    }
    @PostMapping()
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<BaseResponse> createOrderItem(@Valid @RequestBody OrderItemRequestDto orderItemRequestDto){
        return orderItemService.createOrderItem(orderItemRequestDto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")//todo nashta sna vyne, po nashta edhe na vyn
    public ResponseEntity<BaseResponse> deleteOrderItem(@PathVariable UUID id){
        return orderItemService.deleteOrderItem(id);
    }
    @PutMapping("/{id}/{quantity}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<BaseResponse> updateOrderItem(@PathVariable UUID id, @PathVariable Integer quantity){
        return orderItemService.updateOrderItem(id,quantity);
    }
}
