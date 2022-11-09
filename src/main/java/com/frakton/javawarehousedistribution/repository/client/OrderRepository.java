package com.frakton.javawarehousedistribution.repository.client;

import com.frakton.javawarehousedistribution.models.client.Client;
import com.frakton.javawarehousedistribution.models.client.Order;
import com.frakton.javawarehousedistribution.models.client.OrderStatus;
import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrdersByWarehouse_IdAndStatusIn(UUID warehouseID, List<OrderStatus> statuses);
    List<Order> findOrdersByClientAndStatusIn(Client client, Collection<OrderStatus> status);
}
