package models.client;

import models.location.Address;
import models.user.User;

import java.util.UUID;

public class Client {
    private User clientUser;
    private UUID clientId;
    private String clientName;
    private String clientPhoneNumber;
    private Address clientAddress;
    private String clientEmail;

}
