package com.frakton.javawarehousedistribution.services.locationservice;

import com.frakton.javawarehousedistribution.controllers.dto.location.AddressRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.location.AddressResponseDto;
import com.frakton.javawarehousedistribution.models.location.Address;
import com.frakton.javawarehousedistribution.repository.location.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    ModelMapper modelMapper=new ModelMapper();

    public ResponseEntity<List<AddressResponseDto>> getAddress() {
        return ResponseEntity.ok(addressRepository.findAll().
                stream().
                map(address -> modelMapper.map(address,AddressResponseDto.class)).
                collect(Collectors.toList()));
    }

    public ResponseEntity<AddressResponseDto> getAddressById(UUID id) {
        Optional<Address> optionalAddress=addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            Address address=optionalAddress.get();
            return ResponseEntity.ok(modelMapper.map(address,AddressResponseDto.class));
        }else {
           return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<AddressResponseDto> createAddress(AddressRequestDto addressRequestDto) {
        Address address= modelMapper.map(addressRequestDto,Address.class);
        addressRepository.save(address);
        return ResponseEntity.ok(modelMapper.map(address,AddressResponseDto.class));
    }

    public ResponseEntity<AddressResponseDto> deleteAddress(UUID id) {
        Optional<Address> optionalAddress=addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            Address address=optionalAddress.get();
            addressRepository.delete(address);
            return ResponseEntity.ok(modelMapper.map(address,AddressResponseDto.class));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<AddressResponseDto> updateAddress(UUID id, AddressRequestDto addressRequestDto) {
        Optional<Address> optionalAddress=addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            Address address=optionalAddress.get();
            if(addressRequestDto.getCity()!=null){
            address.setCity(addressRequestDto.getCity());
        }
            if(addressRequestDto.getRegion()!=null){
                address.setRegion(addressRequestDto.getRegion());
            }
            if(addressRequestDto.getPostalCode()!=null){
                address.setPostalCode(addressRequestDto.getPostalCode());
            }
            if(addressRequestDto.getStreet()!=null){
                address.setStreet(addressRequestDto.getStreet());
            }
            addressRepository.save(address);
            return ResponseEntity.ok(modelMapper.map(address,AddressResponseDto.class));
        }

        return ResponseEntity.notFound().build();
    }
}
