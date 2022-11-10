package com.frakton.javawarehousedistribution.repository.client;

import com.frakton.javawarehousedistribution.models.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findClientByUserId(UUID user_id);
}
