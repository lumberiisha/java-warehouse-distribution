package com.frakton.javawarehousedistribution.services.userservice;

import com.frakton.javawarehousedistribution.controllers.dto.user.UserRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.user.UserResponseDto;
import com.frakton.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.frakton.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.frakton.javawarehousedistribution.models.user.Role;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    public final UserRepository userRepository;

    public final CreateBaseResponse createBaseResponse;
    ModelMapper modelMapper=new ModelMapper();

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CreateBaseResponse createBaseResponse) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.createBaseResponse = createBaseResponse;
    }
    public ResponseEntity<BaseResponse> getUserById(UUID id) {
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            return createBaseResponse.createResponse("User found", HttpStatus.OK,modelMapper.map(user, UserResponseDto.class));
        }else{
            return createBaseResponse.createBadResponse("User Not Found",HttpStatus.NOT_FOUND);
        }
    }
    public User createUserEntity(UserRequestDto userRequestDto) {
        User user=modelMapper.map(userRequestDto,User.class);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(user);
        return user;
    }

    public ResponseEntity<BaseResponse> deleteUser(UUID id) {
        Optional<User> optionalUser =userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            userRepository.delete(user);
            return createBaseResponse.createResponse("User deleted",HttpStatus.OK,modelMapper.map(user, UserResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("User Not Found",HttpStatus.NOT_FOUND);
        }
    }

    public User getUser(String username){
        return userRepository.findByUserName(username).orElse(null);
    }

}
