package net.ecommerce.microservices.user.controller;

import lombok.RequiredArgsConstructor;
import net.ecommerce.microservices.user.dto.CartItemRequest;
import net.ecommerce.microservices.user.dto.CartResponseDto;
import net.ecommerce.microservices.user.entity.CartItem;
import net.ecommerce.microservices.user.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping()
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest cartItemRequest) {
        if (!cartService.addToCard(userId, cartItemRequest)) {
            return ResponseEntity.badRequest().body("The Item is out of stack or user not found or product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Added To Cart,Thankyou!!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCartItem(@RequestHeader("X-User-ID") String userId, @RequestParam Long productID) {
        Boolean deleted = cartService.deleteItemTOCart(userId, productID);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("/viewCart")
    public ResponseEntity<List<CartItem>> retrieveCart(@RequestHeader("X-User-ID") String userId) {
        return new ResponseEntity<>(cartService.retrieveUserCart(userId),HttpStatus.OK);
    }

}
