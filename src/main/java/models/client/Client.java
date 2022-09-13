package models.client;

import lombok.Getter;
import lombok.Setter;
import models.location.Address;
import models.user.User;

import java.util.UUID;
@Getter
@Setter

public class Client {
    private User user;
    private UUID id;
    private String name;
    private String phoneNumber;
    private Address address;
    private String email;

}
