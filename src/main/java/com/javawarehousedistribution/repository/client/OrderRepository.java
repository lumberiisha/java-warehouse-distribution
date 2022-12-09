package com.javawarehousedistribution.repository.client;

import com.javawarehousedistribution.models.client.Client;
import com.javawarehousedistribution.models.client.Order;
import com.javawarehousedistribution.models.client.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrdersByWarehouse_IdAndStatusIn(UUID warehouseID, List<OrderStatus> statuses);
    List<Order> findOrdersByClientAndStatusIn(Client client, Collection<OrderStatus> status);
}
