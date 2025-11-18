package net.ecommerce.user.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "address")
public class Address {

    @Id
    private String id;
    private String Street;
    private String city;
    private String state;
    private String country;
    private String zipcode;


}
