package com.frakton.javawarehousedistribution.services.userservice;

import com.frakton.javawarehousedistribution.controllers.dto.user.UserRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.user.UserResponseDto;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    public  UserRepository userRepository;
    ModelMapper modelMapper=new ModelMapper();

    public ResponseEntity<List<UserResponseDto>> getUser() {
        return ResponseEntity.ok(userRepository.findAll().
                stream().
                map(user -> modelMapper.map(user, UserResponseDto.class)).
                collect(Collectors.toList()));
    }

    public ResponseEntity<UserResponseDto> getUserById(UUID id) {
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            return ResponseEntity.ok(modelMapper.map(user, UserResponseDto.class));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        User user=modelMapper.map(userRequestDto,User.class);
        userRepository.save(user);
        return ResponseEntity.ok(modelMapper.map(user, UserResponseDto.class));
    }

    public ResponseEntity<UserResponseDto> deleteUser(UUID id) {
        Optional<User> optionalUser =userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            userRepository.delete(user);
            return ResponseEntity.ok(modelMapper.map(user, UserResponseDto.class));
        }
        return ResponseEntity.notFound().build();
    }

//    public User changePassword(UserDto userDto){
//
//        User user1= userRepository.findById(userDto.getId()).get();
//        if (userDto.getOldPassword().equals(user1.getPassword())){
//            user1.setPassword(userDto.getNewPassword());
//        }
//        userRepository.save(user1);
//        return user1;
//    }
}
