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
import java.util.UUID;

@Service
public class OrderService {
    public  List<Order> ordersDB=new ArrayList<>();
    @Autowired
    private final ProductService productService;
    public final OrderItemService orderItemService;

    public OrderService(ProductService productService, OrderItemService orderItemService) {
        this.productService = productService;
        this.orderItemService = orderItemService;
    }


    public List<Order> getOrders(){
        return ordersDB;
    }


    public void createOrder(OrderRequestDto orderRequest) {
        Order order=new Order();
        List<UUID> uuidList=new ArrayList<>();
        for (OrderItemDto orderItem:orderRequest.getOrderItems()) {
            uuidList.add(orderItem.getProductId());
        }
//        List<UUID> productIds //TODO from orderRequest
        List<Product> products = productService.getProductsByIds(uuidList);
        OrderItem orderItem=new OrderItem();
        List<OrderItem> orderItems=new ArrayList<>();
        for (Product product :products) {
            for (OrderItemDto orderItemDto :orderRequest.getOrderItems()) {
                if(product.getId().equals(orderItemDto.getProductId())){
                    orderItem.setQuantity(orderItemDto.getQuantity());
                    orderItem.setProduct(product);
                    orderItems.add(orderItem);
                }
            }
        }
        order.setOrderItems(orderItems);
        ordersDB.add(order);
    }
}
