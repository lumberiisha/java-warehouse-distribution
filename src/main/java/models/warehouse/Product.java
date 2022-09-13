package models.warehouse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class Product {
    private UUID productId;
    private String productName;
    private double productPrice;
    private Date expirationDate;
    private Date manufacturingDate;
    private String productDescription;
}
