package com.frakton.javawarehousedistribution.repository.client;

import com.frakton.javawarehousedistribution.models.client.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
