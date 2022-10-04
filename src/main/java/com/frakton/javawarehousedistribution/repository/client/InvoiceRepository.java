package com.frakton.javawarehousedistribution.repository.client;

import com.frakton.javawarehousedistribution.models.client.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
