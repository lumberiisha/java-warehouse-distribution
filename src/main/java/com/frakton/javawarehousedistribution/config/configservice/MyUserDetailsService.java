package com.frakton.javawarehousedistribution.config.configservice;

import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
   private final UserRepository userRepository;
    ModelMapper modelMapper=new ModelMapper();
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserName(username));
        if(optionalUser.isPresent()){
            User user= optionalUser.get();
            return new MyUserDetails(user);
        }else {
            throw new UsernameNotFoundException("username not found");
        }
    }
}
