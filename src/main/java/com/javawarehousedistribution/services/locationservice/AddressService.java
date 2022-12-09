package com.javawarehousedistribution.services.locationservice;

import com.javawarehousedistribution.controllers.dto.location.AddressRequestDto;
import com.javawarehousedistribution.controllers.dto.location.AddressResponseDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.controllers.dto.utils.CreateBaseResponse;
import com.javawarehousedistribution.models.location.Address;
import com.javawarehousedistribution.repository.location.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CreateBaseResponse createBaseResponse;
    ModelMapper modelMapper=new ModelMapper();

    public AddressService(AddressRepository addressRepository, CreateBaseResponse createBaseResponse) {
        this.addressRepository = addressRepository;
        this.createBaseResponse = createBaseResponse;
    }
    public ResponseEntity<BaseResponse> getAddressById(UUID id) {
        Optional<Address> optionalAddress=addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            Address address=optionalAddress.get();
            return createBaseResponse.createResponse("Address found",HttpStatus.OK,modelMapper.map(address, AddressResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("Address Not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> createAddress(AddressRequestDto addressRequestDto) {
        Address address= modelMapper.map(addressRequestDto,Address.class);
        addressRepository.save(address);
        return createBaseResponse.createResponse("Address created",HttpStatus.CREATED,modelMapper.map(address,AddressResponseDto.class));
    }

    public ResponseEntity<BaseResponse> deleteAddress(UUID id) {
        Optional<Address> optionalAddress=addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            Address address=optionalAddress.get();
            addressRepository.delete(address);
            return createBaseResponse.createResponse("Address deleted",HttpStatus.OK,modelMapper.map(address,AddressResponseDto.class));
        }
        else {
            return createBaseResponse.createBadResponse("Address Not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> updateAddress(UUID id, AddressRequestDto addressRequestDto) {
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
            return createBaseResponse.createResponse("Address updated",HttpStatus.OK,modelMapper.map(address,AddressResponseDto.class));
        }else {
            return createBaseResponse.createBadResponse("Address Not found",HttpStatus.NOT_FOUND);
        }
    }
    public Optional<Address> getAddressEntityById(UUID addressId) {
        return addressRepository.findById(addressId);
    }
}
