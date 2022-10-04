package com.frakton.javawarehousedistribution.repository.client;

import com.frakton.javawarehousedistribution.models.client.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
