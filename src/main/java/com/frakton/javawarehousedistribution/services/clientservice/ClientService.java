package com.frakton.javawarehousedistribution.services.clientservice;

import com.frakton.javawarehousedistribution.controllers.dto.client.ClientRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.client.ClientResponseDto;
import com.frakton.javawarehousedistribution.models.client.Client;
import com.frakton.javawarehousedistribution.models.location.Address;
import com.frakton.javawarehousedistribution.models.user.Role;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.repository.client.ClientRepository;
import com.frakton.javawarehousedistribution.services.locationservice.AddressService;
import com.frakton.javawarehousedistribution.services.userservice.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    ModelMapper modelMapper=new ModelMapper();
    private final UserService userService;
    private final AddressService addressService;
    private final ClientRepository clientRepository;

    public ClientService(UserService userService, AddressService addressService, ClientRepository clientRepository) {
        this.userService = userService;
        this.addressService = addressService;
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<ClientResponseDto> createClient(ClientRequestDto clientRequestDto) {
        Address address =addressService.createAddressAddress(clientRequestDto.getAddress());
        User user=userService.createUserUser(clientRequestDto.getUser());
        user.setRole(Role.CLIENT);
        Client client=modelMapper.map(clientRequestDto,Client.class);
        client.setUser(user);
        client.setAddress(address);
        clientRepository.save(client);
        return ResponseEntity.ok(modelMapper.map(client,ClientResponseDto.class));
    }
}
