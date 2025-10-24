package net.ecommerce.microservices.user.dto;

import lombok.Data;

@Data
public class RequestDto {


    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDto address;
}
