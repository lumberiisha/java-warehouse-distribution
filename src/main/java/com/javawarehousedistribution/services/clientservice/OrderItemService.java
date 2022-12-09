package com.javawarehousedistribution.services.clientservice;

import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.controllers.dto.orderItem.OrderItemRequestDto;
import com.javawarehousedistribution.controllers.dto.orderItem.OrderItemResponseDto;
import com.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.javawarehousedistribution.models.client.OrderItem;
import com.javawarehousedistribution.models.warehouse.Product;
import com.javawarehousedistribution.repository.client.OrderItemRepository;
import com.javawarehousedistribution.services.warehouseservice.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CreateBaseResponse createBaseResponse;
    public ModelMapper modelMapper=new ModelMapper();

    public OrderItemService(OrderItemRepository orderItemRepository, ProductService productService, CreateBaseResponse createBaseResponse) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.createBaseResponse = createBaseResponse;
    }

    public ResponseEntity<BaseResponse> getOrderItemById(UUID id) {
            Optional<OrderItem> optionalOrderItem=orderItemRepository.findById(id);
            if (optionalOrderItem.isPresent()){
                OrderItem orderItem=optionalOrderItem.get();
                return createBaseResponse.createResponse("OrderItem found",HttpStatus.OK, modelMapper.map(orderItem, OrderItemResponseDto.class));
            }else {
                return createBaseResponse.createBadResponse("OrderItem Not found",HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity<BaseResponse> createOrderItem(OrderItemRequestDto orderItemRequestDto) {
        OrderItem orderItem=modelMapper.map(orderItemRequestDto,OrderItem.class);
        Optional<Product> optionalProduct=productService.getProductEntityById(orderItemRequestDto.getProductId());
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            orderItem.setProduct(product);
            orderItemRepository.save(orderItem);
            return createBaseResponse.createResponse("OrderItem created",HttpStatus.CREATED,modelMapper.map(orderItem,OrderItemResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("Product not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> deleteOrderItem(UUID id) {
        Optional<OrderItem> optionalOrderItem=orderItemRepository.findById(id);
        if(optionalOrderItem.isPresent()){
            OrderItem orderItem=optionalOrderItem.get();
            orderItemRepository.delete(orderItem);
            return createBaseResponse.createResponse("OrderItem deleted",HttpStatus.OK,modelMapper.map(optionalOrderItem.get(), OrderItemResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("OrderItem Not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> updateOrderItem(UUID id, Integer quantity) {
        Optional<OrderItem> optionalOrderItem=orderItemRepository.findById(id);
        if(optionalOrderItem.isPresent()){
            OrderItem orderItem=optionalOrderItem.get();
            orderItem.setQuantity(quantity);
            orderItemRepository.save(orderItem);
            return createBaseResponse.createResponse("OrderItem updated",HttpStatus.OK,modelMapper.map(orderItem, OrderItemResponseDto.class));
        }
        else {
            return createBaseResponse.createBadResponse("OrderItem Not found",HttpStatus.NOT_FOUND);
        }
    }

    public List<OrderItem> getOrderItemsInBatch(List<UUID> uuids){
        return orderItemRepository.findAllByIdIn(uuids);
    }
}
