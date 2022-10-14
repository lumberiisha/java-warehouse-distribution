package com.frakton.javawarehousedistribution.repository.user;

import com.frakton.javawarehousedistribution.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    User findByUserName(String userName);
}
