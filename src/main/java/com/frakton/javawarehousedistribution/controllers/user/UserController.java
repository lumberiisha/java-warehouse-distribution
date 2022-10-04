package com.frakton.javawarehousedistribution.controllers.user;

import com.frakton.javawarehousedistribution.controllers.dto.user.UserRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.user.UserResponseDto;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.services.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<List<UserResponseDto>> getUser(){
        return userService.getUser();
    }
    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }
    @PostMapping("/api/user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }
    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }
}
