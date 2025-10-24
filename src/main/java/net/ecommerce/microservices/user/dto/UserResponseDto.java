package net.ecommerce.microservices.user.dto;


import lombok.Data;

@Data
public class UserResponseDto {

        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private AddressDto address;
}
