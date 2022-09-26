package com.frakton.javawarehousedistribution.services.userservice;

import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    public  UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> addUser(User user){
        User user1= new User();
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setId(user.getId());
        user1.setRole(user.getRole());
        userRepository.save(user1);
        return userRepository.findById(user1.getId());
    }
    public Optional<User> deleteUser(UUID id){
        Optional<User> user=userRepository.findById(id);
        userRepository.deleteById(id);
        return user;
    }

}
