package net.ecommerce.microservices.user.repository;

import net.ecommerce.microservices.user.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem,Long> {
}
