package net.ecommerce.microservices.user.service;

import lombok.RequiredArgsConstructor;
import net.ecommerce.microservices.user.dto.CartItemRequest;
import net.ecommerce.microservices.user.dto.CartResponseDto;
import net.ecommerce.microservices.user.entity.CartItem;
import net.ecommerce.microservices.user.entity.Product;
import net.ecommerce.microservices.user.entity.User;
import net.ecommerce.microservices.user.repository.CartRepository;
import net.ecommerce.microservices.user.repository.ProductRepository;
import net.ecommerce.microservices.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Boolean addToCard(String userId, CartItemRequest cartItemRequest) {

        Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProductId());


        if (productOpt.isEmpty()) {
            return false;
        }
        Product product = productOpt.get();
        if (product.getStockQuantity() < cartItemRequest.getQuantity()) {
            return false;
        }
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        CartItem existingItem = cartRepository.findByUserAndProduct(user, product);
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + cartItemRequest.getQuantity();
            existingItem.setQuantity(newQuantity);
            BigDecimal newPrice = existingItem.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity()));
            existingItem.setPrice(newPrice);
            existingItem.setId(existingItem.getId());
            existingItem.setCreatedAt(existingItem.getCreatedAt());
            cartRepository.save(existingItem);

        }
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartRepository.save(cartItem);

        return true;
    }

    public Boolean deleteItemTOCart(String userId, Long productID) {
        Optional<User> user = userRepository.findById(Long.valueOf(userId));
        if (user.isEmpty()) {
            return false;
        }
        Optional<Product> productOpt = productRepository.findById(productID);
        if (productOpt.isEmpty()) {
            return false;
        }
        Product product = productOpt.get();
        CartItem item = cartRepository.findByUserAndProduct(user.get(), product);
        cartRepository.delete(item);
        return true;


    }
    public List<CartResponseDto> retrieveUserCart(String userId) {
    userRepository.findById(Long.valueOf(userId));

    }
}
