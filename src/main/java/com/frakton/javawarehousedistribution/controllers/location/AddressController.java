package com.frakton.javawarehousedistribution.controllers.location;

import com.frakton.javawarehousedistribution.controllers.dto.location.AddressRequestDto;
import com.frakton.javawarehousedistribution.controllers.dto.location.AddressResponseDto;
import com.frakton.javawarehousedistribution.services.locationservice.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AddressController {
    public final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/api/address")
    public ResponseEntity<List<AddressResponseDto>> getAddress(){
        return addressService.getAddress();
    }

    @GetMapping("/api/address/{id}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable UUID id){
        return addressService.getAddressById(id);
    }

    @PostMapping("/api/address")
    public ResponseEntity<AddressResponseDto> createAddress(@RequestBody AddressRequestDto addressRequestDto){
        return addressService.createAddress(addressRequestDto);
    }
    @DeleteMapping("/api/address/{id}")
    public ResponseEntity<AddressResponseDto> deleteAddress(@PathVariable UUID id){
        return addressService.deleteAddress(id);
    }

    @PutMapping("/api/address/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable UUID id, @RequestBody AddressRequestDto addressRequestDto){
        return addressService.updateAddress(id, addressRequestDto);
    }
}
