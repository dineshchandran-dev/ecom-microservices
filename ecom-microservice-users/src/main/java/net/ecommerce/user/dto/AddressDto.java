package net.ecommerce.user.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String Street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
