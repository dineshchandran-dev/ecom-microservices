package net.ecommerce.microservices.user.repository;

import net.ecommerce.microservices.user.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
