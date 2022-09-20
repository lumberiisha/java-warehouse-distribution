package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.models.client.Invoice;
import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.models.client.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    public List<Invoice> invoicesDB=new ArrayList<>();
    @Autowired
    public OrderService orderService;

    public void creatInvoice(Invoice invoice){
        invoice.setOrders(orderService.getOrders());
        invoicesDB.add(invoice);
    }
    public List<Invoice> getInvoices(){
        return invoicesDB;
    }
    public double getTotalPrice(List<Order> listOfOrders) {
        double invoiceTotalPrice=0;
        for (Order order:listOfOrders) {
            for (OrderItem orderItem:order.getOrderItems()) {
                invoiceTotalPrice=invoiceTotalPrice+orderItem.getQuantity()*orderItem.getProduct().getPrice();
            }
        }
        return invoiceTotalPrice;
    }

}
