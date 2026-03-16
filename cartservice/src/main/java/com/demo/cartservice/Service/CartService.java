package com.demo.cartservice.Service;

import com.demo.cartservice.DTO.Product;
import com.demo.cartservice.DTO.User;
import com.demo.cartservice.Entity.Cart;
import com.demo.cartservice.feignclient.ProductClient;
import com.demo.cartservice.Repository.CartRepository;
import com.demo.cartservice.feignclient.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;
    private final UserClient userClient;

    // Add to Cart
    public Cart addToCart(String userId, String productId, Integer quantity) {

        // Validate user
        User user = userClient.getUserById(userId);


        // Fetch product
        Product product = productClient.getProduct(productId);

        var existing = cartRepository
                .findByUserIdAndProductId(userId, productId);


        if (existing.isPresent()) {
            Cart cart = existing.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            return cartRepository.save(cart);
        }

        Cart cart = Cart.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .price(product.getPrice())
                .build();

        return cartRepository.save(cart);
    }

    // Get User Cart
    public List<Cart> getUserCart(String userId) {

        return cartRepository.findByUserId(userId);
    }

    // Update Quantity
    public Cart updateQuantity(String userId, String productId, Integer quantity) {
        Cart cart = cartRepository
                .findByUserIdAndProductId(userId, productId)
                .orElseThrow();

        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    // Remove Product
    public void removeProduct(String id) {
        cartRepository.deleteById(id);
    }

    // Clear Cart
    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }
}
