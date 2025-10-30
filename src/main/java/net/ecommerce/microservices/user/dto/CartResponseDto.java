package net.ecommerce.microservices.user.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import net.ecommerce.microservices.user.entity.Product;
import net.ecommerce.microservices.user.entity.User;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponseDto {
    private User user;
    private List<Product> product;
    private Integer quantity;
    private BigDecimal price;
}
