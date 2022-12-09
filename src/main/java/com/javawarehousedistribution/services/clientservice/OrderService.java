package com.javawarehousedistribution.services.clientservice;

import com.javawarehousedistribution.controllers.dto.location.AddressResponseDto;
import com.javawarehousedistribution.controllers.dto.order.OrderRequestDto;
import com.javawarehousedistribution.controllers.dto.order.OrderResponseDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.javawarehousedistribution.models.client.Client;
import com.javawarehousedistribution.models.client.Order;
import com.javawarehousedistribution.models.client.OrderItem;
import com.javawarehousedistribution.models.client.OrderStatus;
import com.javawarehousedistribution.models.location.Address;
import com.javawarehousedistribution.models.user.Role;
import com.javawarehousedistribution.models.user.User;
import com.javawarehousedistribution.models.warehouse.Warehouse;
import com.javawarehousedistribution.repository.client.OrderRepository;
import com.javawarehousedistribution.services.warehouseservice.WarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    public final OrderRepository orderRepository;
    public final OrderItemService orderItemService;
    private final ClientService clientService;
    private final WarehouseService warehouseService;
    private final CreateBaseResponse createBaseResponse;
    public ModelMapper modelMapper=new ModelMapper();

    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService, ClientService clientService, WarehouseService warehouseService, CreateBaseResponse createBaseResponse) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.clientService = clientService;
        this.warehouseService = warehouseService;
        this.createBaseResponse = createBaseResponse;
    }

    public ResponseEntity<BaseResponse> getOrders() {
        List<Order> orderList=orderRepository.findAll();
        return createBaseResponse.createResponse("Orders found", HttpStatus.OK,orderResponseDtoList(orderList));
    }

    public ResponseEntity<BaseResponse> getOrderById(UUID id) {
        Optional<Order> orderOptional=orderRepository.findById(id);
        if(orderOptional.isPresent()){
            Order order=orderOptional.get();
            return createBaseResponse.createResponse("Order found",HttpStatus.OK,modelMapper.map(order, OrderResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("Order Not found",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<BaseResponse> getClientOrders(User user) {
        Client client=clientService.getClientByUserId(user.getId());
        List<OrderStatus>orderStatuses=getOrderStatusOfUser(user);
        List<Order> orderList=orderRepository.findOrdersByClientAndStatusIn(client,orderStatuses);
        return createBaseResponse.createResponse("Orders found",HttpStatus.OK,orderResponseDtoList(orderList));
    }

    public ResponseEntity<BaseResponse> getOrdersByRole(User user,UUID warehouseId){
        List<OrderStatus> orderStatuses = getOrderStatusOfUser(user);
        List<Order> orderList = orderRepository.findOrdersByWarehouse_IdAndStatusIn(warehouseId,orderStatuses);
        return createBaseResponse.createResponse("Orders found",HttpStatus.OK,orderResponseDtoList(orderList));
    }

   private List<OrderStatus> getOrderStatusOfUser(User user){
        List<OrderStatus> orderStatuses = new ArrayList<>();
        if (user.getRole().equals(Role.OFFICE_WORKER)||user.getRole().equals(Role.CLIENT)){
            orderStatuses.add(OrderStatus.DELIVERED);
            orderStatuses.add(OrderStatus.CREATED);
            orderStatuses.add(OrderStatus.WAITING_FOR_DELIVERY);
            orderStatuses.add(OrderStatus.IN_PROGRESS);
        } else if (user.getRole().equals(Role.WAREHOUSE_WORKER)) {
            orderStatuses.add(OrderStatus.IN_PROGRESS);
        } else if (user.getRole().equals(Role.DELIVERY)) {
            orderStatuses.add(OrderStatus.WAITING_FOR_DELIVERY);
        }
        return orderStatuses;
    }

    public ResponseEntity<BaseResponse> createOrder(OrderRequestDto orderRequestDto, User user, UUID warehouseId) {
        Client client=clientService.getClientByUserId(user.getId());
        Optional<Warehouse> optionalWarehouse=warehouseService.getWarehousesEntity(warehouseId);
        if(optionalWarehouse.isPresent()){
            Warehouse warehouse=optionalWarehouse.get();
            List<OrderItem> orderItemList=orderItemService.getOrderItemsInBatch(orderRequestDto.getOrderItemsId());
            Order order= new Order();
            order.setOrderDate(new Date());
            order.setStatus(OrderStatus.CREATED);
            order.setOrderItems(orderItemList);
            order.setClient(client);
            order.setWarehouse(warehouse);
            orderRepository.save(order);
            OrderResponseDto orderResponseDto=modelMapper.map(order,OrderResponseDto.class);
            return createBaseResponse.createResponse("Order created",HttpStatus.CREATED,orderResponseDto);

        }else {
            return createBaseResponse.createBadResponse("Warehouse not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse>changeOrderStatus(User user, OrderStatus status, UUID orderId){
        boolean isAllowed = isUserAllowed(user,status);
        if (isAllowed){
            Optional<Order> orderOptional=orderRepository.findById(orderId);
            if(orderOptional.isPresent()){
                Order order=orderOptional.get();
                order.setStatus(status);
                orderRepository.save(order);
                return createBaseResponse.createResponse("order status changed",HttpStatus.OK,modelMapper.map(order,OrderResponseDto.class));
            }else {
                return createBaseResponse.createBadResponse("order not found",HttpStatus.NOT_FOUND);
            }
        }else {
            return createBaseResponse.createBadResponse("not allowed",HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    private boolean isUserAllowed(User user, OrderStatus status){
        if(user.getRole().equals(Role.OFFICE_WORKER)){
            return true;
        }else if(user.getRole().equals(Role.WAREHOUSE_WORKER)&&status.equals(OrderStatus.WAITING_FOR_DELIVERY)){
            return true;
        }else return user.getRole().equals(Role.DELIVERY) && status.equals(OrderStatus.DELIVERED);
    }

    private   List<OrderResponseDto> orderResponseDtoList(List<Order> orderList){
        return orderList.stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<BaseResponse> getClientAddressFromOrder(UUID orderId) {
        Optional<Order> optionalOrder=orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();
            Address address=order.getClient().getAddress();
            if(address!=null){
                return createBaseResponse.createResponse("client address",HttpStatus.OK, modelMapper.map(address, AddressResponseDto.class));
            }else
                return createBaseResponse.createBadResponse("address not found",HttpStatus.NOT_FOUND);
        }else {
            return createBaseResponse.createBadResponse("order not found",HttpStatus.NOT_FOUND);
        }
    }
}
