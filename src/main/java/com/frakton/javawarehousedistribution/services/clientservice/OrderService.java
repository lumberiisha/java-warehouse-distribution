package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.controllers.dto.order.OrderRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.order.OrderResponseDto;
import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.models.client.OrderItem;
import com.frakton.javawarehousedistribution.repository.client.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public OrderItemService orderItemService;
    public ModelMapper modelMapper=new ModelMapper();
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        List<Order> orderList=orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList=orderList.
                stream().
                map(order -> modelMapper.map(order, OrderResponseDto.class)).
                collect(Collectors.toList());
        return ResponseEntity.ok(orderResponseDtoList);
    }

    public ResponseEntity<OrderResponseDto> getOrderById(UUID id) {
        Optional<Order> orderOptional=orderRepository.findById(id);
        if(orderOptional.isPresent()){
            Order order=orderOptional.get();
            return ResponseEntity.ok(modelMapper.map(order,OrderResponseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<OrderResponseDto> createOrder(OrderRequestDto orderRequestDto) {
        List<OrderItem> orderItemList=orderRequestDto.getOrderItemsId().
                stream().
                map(uuid -> modelMapper.map(orderItemService.getOrderItemById(uuid).getBody(), OrderItem.class)).
                collect(Collectors.toList());
        Order order= modelMapper.map(orderRequestDto,Order.class);
        order.setOrderItems(orderItemList);
        orderRepository.save(order);
        return ResponseEntity.ok(modelMapper.map(order,OrderResponseDto.class));
    }

    public ResponseEntity<OrderResponseDto> deleteOrder(UUID id) {
        Order order= modelMapper.map(getOrderEntityById(id).getBody(),Order.class);
        orderRepository.delete(order);
        OrderResponseDto orderResponseDto=modelMapper.map(order,OrderResponseDto.class);
        return ResponseEntity.ok(orderResponseDto);
    }
    public ResponseEntity<Order> getOrderEntityById(UUID id){
        Optional<Order> optionalOrder=orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();
            return ResponseEntity.ok(order);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
