package com.frakton.javawarehousedistribution.controllers.client;

import com.frakton.javawarehousedistribution.controllers.dto.invoice.InvoiceRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.invoice.InvoiceResponseDto;
import com.frakton.javawarehousedistribution.models.client.Invoice;
import com.frakton.javawarehousedistribution.services.clientservice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.UUID;

@RestController
public class InvoiceController {
    @Autowired
    public InvoiceService invoiceService;

    @GetMapping("/api/invoice")
    public ResponseEntity<List<InvoiceResponseDto>> getInvoices(){
        return invoiceService.getInvoices();
    }
    @GetMapping("/api/invoice/{id}")
    public ResponseEntity<InvoiceResponseDto> getInvoiceById(@PathVariable UUID id){
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping("/api/invoice")
    public ResponseEntity<InvoiceResponseDto> createInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        return invoiceService.createInvoice(invoiceRequestDto);
    }
    @DeleteMapping("/api/invoice/{id}")
    public ResponseEntity<InvoiceResponseDto> deleteInvoice(@PathVariable UUID id){
        return invoiceService.deleteInvoice(id);
    }
}
