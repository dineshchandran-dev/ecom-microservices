package net.ecommerce.microservices.user.repository;

import net.ecommerce.microservices.user.entity.CartItem;
import net.ecommerce.microservices.user.entity.Product;
import net.ecommerce.microservices.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem,Long> {

    CartItem findByUserAndProduct(User user, Product product);

   List<CartItem> findByUser(User user);


}
