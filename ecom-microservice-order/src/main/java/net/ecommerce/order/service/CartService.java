package net.ecommerce.order.service;

import lombok.RequiredArgsConstructor;
import net.ecommerce.order.dto.CartItemRequest;
import net.ecommerce.order.entity.CartItem;
import net.ecommerce.order.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Boolean addToCard(String userId, CartItemRequest cartItemRequest) {
//
//        Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProductId());
//
//
//        if (productOpt.isEmpty()) {
//            return false;
//        }
//        Product product = productOpt.get();
//        if (product.getStockQuantity() < cartItemRequest.getQuantity()) {
//            return false;
//        }
//        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
//        if (userOpt.isEmpty()) {
//            return false;
//        }
//        User user = userOpt.get();
        CartItem existingItem = cartRepository.findByUserIdAndProductId(userId, cartItemRequest.getProductId());
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
        cartItem.setUserId(userId);
        cartItem.setProductId(cartItemRequest.getProductId());
        cartItem.setPrice(BigDecimal.valueOf(444));
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartRepository.save(cartItem);

        return true;
    }

    public boolean deleteItemFromCart(String userId, String productId) {
        CartItem cartItem = cartRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            cartRepository.delete(cartItem);
            return true;
        }
        return false;
    }

//    public List<CartItem> retrieveUserCart(String userId) {
////        User user = userRepository.findById(Long.valueOf(userId))
////                .orElseThrow(() -> new RuntimeException("User Not Found"));
////
////        List<CartItem> cartItems = cartRepository.findAllByUser(user);
////
////        return cartItems.stream()
////                .map(this::mapToCartResponse)
////                .collect(Collectors.toList());
//
//        return userRepository.findById(Long.valueOf(userId)).map(cartRepository::findByUserId).orElseGet(List::of);
//    }


    public List<CartItem> getCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }

//    private CartResponseDto mapToCartResponse(CartItem cartItem) {
//        CartResponseDto cartResponseDto = new CartResponseDto();
//        cartResponseDto.setUser(cartItem.getUser());
//        cartResponseDto.setProduct(Collections.singletonList(cartItem.getProduct()));
//        cartResponseDto.setPrice(cartItem.getPrice());
//        cartResponseDto.setQuantity(cartItem.getQuantity());
//        return cartResponseDto;
//    }

}
