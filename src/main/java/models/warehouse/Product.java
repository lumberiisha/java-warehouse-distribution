package models.warehouse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class Product {
    private UUID id;
    private String name;
    private double price;
    private Date expirationDate;
    private Date manufacturingDate;
    private String description;
}
