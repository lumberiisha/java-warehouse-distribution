package com.javawarehousedistribution.repository.client;

import com.javawarehousedistribution.models.client.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    List<OrderItem> findAllByIdIn(List<UUID> id);
}
