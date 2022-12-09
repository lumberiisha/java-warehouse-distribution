package com.javawarehousedistribution.controllers.location;

import com.javawarehousedistribution.controllers.dto.location.AddressRequestDto;
import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.services.locationservice.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CLIENT')")
    public ResponseEntity<BaseResponse> getAddressById(@PathVariable UUID id){
        return addressService.getAddressById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('CLIENT','OFFICE_WORKER','ADMIN')")
    public ResponseEntity<BaseResponse> createAddress(@RequestBody AddressRequestDto addressRequestDto){
        return addressService.createAddress(addressRequestDto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<BaseResponse> deleteAddress(@PathVariable UUID id){
        return addressService.deleteAddress(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<BaseResponse> updateAddress(@PathVariable UUID id,@RequestBody AddressRequestDto addressRequestDto){
        return addressService.updateAddress(id, addressRequestDto);
    }
}
