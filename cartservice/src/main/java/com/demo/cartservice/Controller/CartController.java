package com.demo.cartservice.Controller;


import com.demo.cartservice.Entity.Cart;
import com.demo.cartservice.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(
            @RequestParam String userId,
            @RequestParam String productId,
            @RequestParam Integer quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable String userId) {
        return cartService.getUserCart(userId);
    }

    @PutMapping("/update")
    public Cart updateQuantity(
            @RequestParam String userId,
            @RequestParam String productId,
            @RequestParam Integer quantity) {
        return cartService.updateQuantity(userId, productId, quantity);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable String id) {
        cartService.removeProduct(id);
    }

    @DeleteMapping("/clear/{userId}")
    public void clear(@PathVariable String userId) {
        cartService.clearCart(userId);
    }
}