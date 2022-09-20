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
import java.util.stream.Collectors;

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


    public Order createOrder(OrderRequestDto orderRequest) {
        Order order=new Order();
        List<UUID> uuidList= orderRequest.getOrderItems().stream().map(OrderItemDto::getProductId).collect(Collectors.toList());
        List<OrderItem> orderItems = new ArrayList<>();
        List<Product> products = productService.getProductsByIds(uuidList);
        for (OrderItemDto orderItemDto: orderRequest.getOrderItems()){
            //TODO getProduct from products based on productId of orderItemDto
            //TODO create orderitem
            //TODO add orderitem to the list
        }
        //TODO (later) save OrderItems to the repo
        order.setOrderItems(orderItems);
//        OrderItem orderItem=new OrderItem();
////        List<OrderItem> orderItems=new ArrayList<>();
//        for (Product product :products) {
//            for (OrderItemDto orderItemDto :orderRequest.getOrderItems()) {
//                if(product.getId().equals(orderItemDto.getProductId())){
//                    orderItem.setQuantity(orderItemDto.getQuantity());
//                    orderItem.setProduct(product);
//                    orderItems.add(orderItem);
//                }
//            }
//        }
        order.setOrderItems(orderItems);
        ordersDB.add(order);
        return order;
    }
}
