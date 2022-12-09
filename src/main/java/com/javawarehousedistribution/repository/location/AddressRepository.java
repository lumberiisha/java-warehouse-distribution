package com.javawarehousedistribution.repository.location;

import com.javawarehousedistribution.models.location.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
