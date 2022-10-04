package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.controllers.dto.orderItem.OrderItemRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.orderItem.OrderItemResponseDto;
import com.frakton.javawarehousedistribution.controllers.dto.product.ProductResponseDto;
import com.frakton.javawarehousedistribution.models.client.OrderItem;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.repository.client.OrderItemRepository;
import com.frakton.javawarehousedistribution.repository.warehouse.ProductRepository;
import com.frakton.javawarehousedistribution.services.warehouseservice.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    @Autowired
    public OrderItemRepository orderItemRepository;
    @Autowired
    public ProductService productService;
    public ModelMapper modelMapper=new ModelMapper();
    public ResponseEntity<OrderItemResponseDto> getOrderItemById(UUID id) {
        Optional<OrderItem> optionalOrderItem=orderItemRepository.findById(id);
        if(optionalOrderItem.isPresent()){
            OrderItem orderItem=optionalOrderItem.get();
            return ResponseEntity.ok(modelMapper.map(orderItem, OrderItemResponseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<OrderItemResponseDto>> getOrderItems() {
        List<OrderItem> orderItemList=orderItemRepository.findAll();
        return ResponseEntity.ok(orderItemList.
                stream().
                map(orderItem -> modelMapper.map(orderItem, OrderItemResponseDto.class)).
                collect(Collectors.toList()));
    }

    public ResponseEntity<OrderItemResponseDto> createOrderItem(OrderItemRequestDto orderItemRequestDto) {
        OrderItem orderItem=modelMapper.map(orderItemRequestDto,OrderItem.class);
        orderItem.setProduct(productService.getProductEntityById(orderItemRequestDto.getProductId()).getBody());
        orderItemRepository.save(orderItem);
        return ResponseEntity.ok(modelMapper.map(orderItem, OrderItemResponseDto.class));
    }

    public ResponseEntity<OrderItemResponseDto> deleteOrderItem(UUID id) {
        Optional<OrderItem> optionalOrderItem=orderItemRepository.findById(id);
        if(optionalOrderItem.isPresent()){
            orderItemRepository.delete(optionalOrderItem.get());
            return ResponseEntity.ok(modelMapper.map(optionalOrderItem.get(), OrderItemResponseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<OrderItemResponseDto> updateOrderItem(UUID id, Integer quantity) {
        Optional<OrderItem> optionalOrderItem=orderItemRepository.findById(id);
        if(optionalOrderItem.isPresent()){
            OrderItem orderItem=optionalOrderItem.get();
            orderItem.setQuantity(quantity);
            orderItemRepository.save(orderItem);
            return ResponseEntity.ok(modelMapper.map(orderItem,OrderItemResponseDto.class));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
