package com.javawarehousedistribution.repository.warehouse;

import com.javawarehousedistribution.models.warehouse.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByIdIn(List<UUID> id);
}
