package com.javawarehousedistribution.controllers.client;

import com.javawarehousedistribution.controllers.dto.utils.BaseResponse;
import com.javawarehousedistribution.services.clientservice.ClientService;
import com.javawarehousedistribution.services.userservice.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;
    private final UserService userService;
    public ClientController(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }
    @PatchMapping("/{addressId}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<BaseResponse> setAddress(Authentication authentication, @PathVariable UUID addressId){
        return clientService.setAddress(userService.getUser(authentication.getPrincipal().toString()), addressId);
    }

}
