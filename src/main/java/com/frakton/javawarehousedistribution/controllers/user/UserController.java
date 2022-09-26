package com.frakton.javawarehousedistribution.controllers.user;

import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.services.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/api/user")
    public Optional<User> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/api/user")
    public Optional<User> deleteUser(@RequestBody UUID id){
        return userService.deleteUser(id);
    }
}
