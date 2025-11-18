package net.ecommerce.order.repository;

import net.ecommerce.order.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem,Long> {

    CartItem findByUserIdAndProductId (String userId, String productId);
   List<CartItem> findByUserId(String  userId);


    void deleteByUserId(String userId);
}
