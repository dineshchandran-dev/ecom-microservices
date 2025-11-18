package net.ecommerce.user.dto;

import lombok.Data;
import net.ecommerce.user.dto.AddressDto;

@Data
public class RequestDto {


    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDto address;
}
