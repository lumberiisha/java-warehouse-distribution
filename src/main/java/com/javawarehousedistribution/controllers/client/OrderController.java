package com.javawarehousedistribution.controllers.client;

import com.javawarehousedistribution.controllers.dto.order.OrderRequestDto;
import com.javawarehousedistribution.controllers.dto.order.OrderStatusRequestDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.services.clientservice.OrderService;
import com.javawarehousedistribution.services.userservice.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CLIENT','OFFICE_WORKER')")
    public ResponseEntity<BaseResponse> getOrderById(@PathVariable UUID id){
        return orderService.getOrderById(id);
    }
    @GetMapping("/client")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<BaseResponse> getClientOrders(Authentication authentication){
        return orderService.getClientOrders(userService.getUser(authentication.getPrincipal().toString()));
    }
    @GetMapping("/warehouse/{warehouseId}")
    @PreAuthorize("hasAnyAuthority('CLIENT','OFFICE_WORKER','WAREHOUSE_WORKER','DELIVERY')")
    public ResponseEntity<BaseResponse> getOrdersByRole(Authentication authentication,@PathVariable UUID warehouseId){
        return orderService.getOrdersByRole(userService.getUser(authentication.getPrincipal().toString()), warehouseId);
    }
    @GetMapping("/address/{orderId}")
    @PreAuthorize("hasAuthority('DELIVERY')")
    public ResponseEntity<BaseResponse> getClientAddressFromOrder(@PathVariable UUID orderId){
        return orderService.getClientAddressFromOrder(orderId);
    }
    @PostMapping("/{warehouseId}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<BaseResponse> createOrder(Authentication authentication, @RequestBody OrderRequestDto orderRequestDto, @PathVariable UUID warehouseId){
        return orderService.createOrder(orderRequestDto, userService.getUser(authentication.getPrincipal().toString()), warehouseId);
    }
    @PatchMapping("/{orderId}")
    @PreAuthorize("hasAnyAuthority('OFFICE_WORKER','DELIVERY','WAREHOUSE_WORKER')")
    public ResponseEntity<BaseResponse> changeOrderStatus(@PathVariable UUID orderId,Authentication authentication,@RequestBody OrderStatusRequestDto orderStatusRequestDto ){
        return orderService.changeOrderStatus(userService.getUser(authentication.getPrincipal().toString()),orderStatusRequestDto.getStatus(), orderId);
    }
}
