package com.frakton.javawarehousedistribution.repository.warehouse;

import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
}
