package com.javawarehousedistribution.services.clientservice;

import com.javawarehousedistribution.controllers.dto.client.ClientRequestDto;
import com.javawarehousedistribution.controllers.dto.client.ClientResponseDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.javawarehousedistribution.models.client.Client;
import com.javawarehousedistribution.models.location.Address;
import com.javawarehousedistribution.models.user.Role;
import com.javawarehousedistribution.models.user.User;
import com.javawarehousedistribution.repository.client.ClientRepository;
import com.javawarehousedistribution.services.locationservice.AddressService;
import com.javawarehousedistribution.services.userservice.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    ModelMapper modelMapper=new ModelMapper();
    private final UserService userService;
    private final AddressService addressService;
    private final ClientRepository clientRepository;
    private final CreateBaseResponse createBaseResponse;

    public ClientService(UserService userService, AddressService addressService, ClientRepository clientRepository, CreateBaseResponse createBaseResponse) {
        this.userService = userService;
        this.addressService = addressService;
        this.clientRepository = clientRepository;
        this.createBaseResponse = createBaseResponse;
    }

    public ResponseEntity<BaseResponse> createClient(ClientRequestDto clientRequestDto) {
        User user=userService.createUserEntity(clientRequestDto.getUser());
        user.setRole(Role.CLIENT);
        Client client=modelMapper.map(clientRequestDto,Client.class);
        client.setUser(user);
        clientRepository.save(client);
        return createBaseResponse.createResponse("User Created", HttpStatus.OK,modelMapper.map(client, ClientResponseDto.class));
    }

    public ResponseEntity<BaseResponse> setAddress(User user,UUID addressId){
        Client client=getClientByUserId(user.getId());
        Optional<Address> optionalAddress =addressService.getAddressEntityById(addressId);
        if(optionalAddress.isPresent()){
            Address address=optionalAddress.get();
            client.setAddress(address);
            clientRepository.save(client);
            return createBaseResponse.createResponse("address set to client",HttpStatus.OK,modelMapper.map(client,ClientResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("address not found",HttpStatus.NOT_FOUND);
        }
    }
    public Client getClientByUserId(UUID id){
        return clientRepository.findClientByUserId(id);
    }
}
