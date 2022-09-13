package models.client;

import lombok.Getter;
import lombok.Setter;
import models.location.Address;
import models.user.User;

import java.util.UUID;
@Getter
@Setter

public class Client {
    private User clientUser;
    private UUID clientId;
    private String clientName;
    private String clientPhoneNumber;
    private Address clientAddress;
    private String clientEmail;

}
