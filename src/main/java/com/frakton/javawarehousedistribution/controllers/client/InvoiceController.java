package com.frakton.javawarehousedistribution.controllers.client;

import com.frakton.javawarehousedistribution.models.client.Invoice;
import com.frakton.javawarehousedistribution.services.clientservice.InvoiceService;
import com.frakton.javawarehousedistribution.services.clientservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    public InvoiceService invoiceService;
    @PostMapping("/api/invoice1")
    public void creatInvoice(@RequestBody Invoice invoice){
        invoiceService.creatInvoice(invoice);
    }
    @GetMapping("/api/invoice")
    public List<Invoice> getInvoice(){
        return invoiceService.getInvoices();
    }

}
