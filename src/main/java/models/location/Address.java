package models.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private Region region;
    private String street;
    private String city;
    private int postalCode;
}
