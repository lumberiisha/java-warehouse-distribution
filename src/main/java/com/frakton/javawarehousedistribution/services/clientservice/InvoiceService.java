package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.models.client.Invoice;
import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.models.client.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class InvoiceService {
    public List<Invoice> invoicesDB=new ArrayList<>();
    @Autowired
    public OrderService orderService;

    public void creatInvoice(){
        Invoice invoice=new Invoice();
        invoice.setId(UUID.randomUUID());
        invoice.setOrders(orderService.getOrders());
        invoice.setTotalPrice(getTotalPrice(invoice.getOrders()));
        invoicesDB.add(invoice);
    }
    public List<Invoice> getInvoices(){
        return invoicesDB;
    }
    public double getTotalPrice(List<Order> listOfOrders) {
        double invoiceTotalPrice=0;
//        for (Order order :listOfOrders) {
//            double quantity= order.getOrderItems().stream().collect(Collectors.averagingDouble(OrderItem::getQuantity));
//            double price=order.getOrderItems().stream().collect(Collectors.averagingDouble(orderItem ->orderItem.getProduct().getPrice()));
//            invoiceTotalPrice=invoiceTotalPrice+quantity*price;
//
//        }
        for (Order order:listOfOrders) {
            for (OrderItem orderItem:order.getOrderItems()) {
                invoiceTotalPrice=invoiceTotalPrice+orderItem.getQuantity()*orderItem.getProduct().getPrice();
            }
        }
        return invoiceTotalPrice;
    }

}
