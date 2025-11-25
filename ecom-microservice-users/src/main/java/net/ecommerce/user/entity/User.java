package net.ecommerce.user.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id

    private String id;

    private String firstName;

    private String lastName;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String phoneNumber;

    private UserRole role = UserRole.CUSTOMER;

    private Address address;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedDate
    private LocalDateTime UpdatedAt;



}
