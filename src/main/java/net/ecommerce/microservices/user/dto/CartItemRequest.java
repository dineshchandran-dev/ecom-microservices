package net.ecommerce.microservices.user.dto;

import lombok.Data;

@Data
public class CartItemRequest {

    private Long productId;
    private Integer quantity;
}
