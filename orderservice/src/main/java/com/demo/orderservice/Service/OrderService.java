package com.demo.orderservice.Service;

import com.demo.orderservice.Client.CartClient;
import com.demo.orderservice.Client.InventoryClient;
import com.demo.orderservice.DTO.CartResponse;
import com.demo.orderservice.DTO.InventoryRequest;
import com.demo.orderservice.Entity.Order;
import com.demo.orderservice.Entity.OrderItem;
import com.demo.orderservice.Enum.OrderStatus;
import com.demo.orderservice.Repository.OrderItemReop;
import com.demo.orderservice.Repository.OrderRepo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartClient cartClient;
    private final InventoryClient inventoryClient;
    private final OrderRepo orderRepo;
    private final OrderItemReop orderItemReop;

    public Order createOrder(String userId) {

        // 1️⃣ Get cart items
        CartResponse cart = cartClient.getCart(userId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // 2️⃣ Create order
        Order order = Order.builder()
                .userId(userId)
                .totalAmount(cart.getTotalAmount())
                .status(OrderStatus.CREATED)
                .build();

        Order savedOrder = orderRepo.save(order);

        // 3️⃣ Save order items
        cart.getItems().forEach(item -> {

            OrderItem orderItem = OrderItem.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .price(item.getPrice())
                    .order(savedOrder)
                    .build();

            orderItemReop.save(orderItem);

            // 4️⃣ Reduce stock
            inventoryClient.reduceStock(
                    new InventoryRequest(item.getProductId(), item.getQuantity())
            );
        });


        return savedOrder;
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        orderRepo.save(order);
    }
}
