package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.models.client.Invoice;
import com.frakton.javawarehousedistribution.models.client.InvoiceStatus;
import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
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
        invoicesDB.add(new Invoice(InvoiceStatus.UNPAID,
                getTotalPrice(orderService.getOrders()),
                orderService.getOrders()));
    }

    public List<Invoice> getInvoices(){
        return invoicesDB;
    }
    public double getTotalPrice(List<Order> listOfOrders) {
        double invoiceTotalPrice=0;
        for (Order order :listOfOrders) {
            double orderTotalPrice=0;
            for (Product product :order.getProducts()) {
                orderTotalPrice=orderTotalPrice+product.getPrice();
            }
            invoiceTotalPrice=invoiceTotalPrice+orderTotalPrice;
        }
        return invoiceTotalPrice;
    }

}
