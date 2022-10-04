package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.controllers.dto.invoice.InvoiceRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.invoice.InvoiceResponseDto;
import com.frakton.javawarehousedistribution.controllers.dto.order.OrderResponseDto;
import com.frakton.javawarehousedistribution.models.client.Invoice;
import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.models.client.OrderItem;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.repository.client.InvoiceRepository;
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
public class InvoiceService {

    @Autowired
    public InvoiceRepository invoiceRepository;
    @Autowired
    public OrderService orderService;
    ModelMapper modelMapper=new ModelMapper();
    public ResponseEntity<List<InvoiceResponseDto>> getInvoices() {
        return ResponseEntity.ok(invoiceRepository.findAll().
                stream().
                map(invoice -> modelMapper.map(invoice, InvoiceResponseDto.class)).
                collect(Collectors.toList()));
    }

    public ResponseEntity<InvoiceResponseDto> getInvoiceById(UUID id) {
        Optional<Invoice> optionalInvoice=invoiceRepository.findById(id);
        if(optionalInvoice.isPresent()){
            Invoice invoice=optionalInvoice.get();
            return ResponseEntity.ok(modelMapper.map(invoice,InvoiceResponseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //TODO
    public ResponseEntity<InvoiceResponseDto> createInvoice(InvoiceRequestDto invoiceRequestDto) {
        Order order =orderService.getOrderEntityById(invoiceRequestDto.getOrderId()).getBody();
        Invoice invoice= modelMapper.map(invoiceRequestDto,Invoice.class);
        invoice.setOrder(order);
        invoice.setTotalPrice(totalPrice(order));
        invoiceRepository.save(invoice);
        return ResponseEntity.ok(modelMapper.map(invoice, InvoiceResponseDto.class));
    }

    public ResponseEntity<InvoiceResponseDto> deleteInvoice(UUID id) {
        Optional<Invoice> optionalInvoice=invoiceRepository.findById(id);
        if(optionalInvoice.isPresent()){
            Invoice invoice=optionalInvoice.get();
            invoiceRepository.delete(invoice);
            return ResponseEntity.ok(modelMapper.map(invoice, InvoiceResponseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    public Double totalPrice(Order order){
        double calculateTotalPrice=0;
        for (OrderItem orderItem :order.getOrderItems()) {
            calculateTotalPrice=calculateTotalPrice+orderItem.getQuantity()*orderItem.getProduct().getPrice();
        }
        return calculateTotalPrice;
    }
}
