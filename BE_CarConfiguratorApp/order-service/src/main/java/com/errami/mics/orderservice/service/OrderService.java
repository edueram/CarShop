package com.errami.mics.orderservice.service;

import com.errami.mics.orderservice.dto.OrderRequest;
import com.errami.mics.orderservice.dto.OrderResponse;
import com.errami.mics.orderservice.model.Order;
import com.errami.mics.orderservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public Order placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .id(orderRequest.id())
                .userId(orderRequest.userId())
                .configurationId(orderRequest.configurationId())
                .orderDate(LocalDateTime.now())
                .finalPrice(orderRequest.finalPrice())
                .status("PENDING")
                .build();

        orderRepository.save(order);
        return order;
    }

    public OrderResponse get_OrderById(UUID orderId) {
        Order order= orderRepository.findById(orderId).orElse(null);
        return  new OrderResponse(order.getId(),order.getUserId(),order.getConfigurationId(),order.getOrderDate(),
                order.getFinalPrice(), order.getStatus());
    }

    public List<OrderResponse> getAll_UserOrders(String userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);

        return orders.stream().map(
                order ->  new OrderResponse(order.getId(),order.getUserId(),order.getConfigurationId(),order.getOrderDate(),
                order.getFinalPrice(), order.getStatus())).toList();
    }

    public OrderResponse get_OrderByConfiguration(UUID configurationId) {
        Order order= orderRepository.findByConfigurationId(configurationId);
        return  new OrderResponse(order.getId(),order.getUserId(),order.getConfigurationId(),order.getOrderDate(),
                order.getFinalPrice(), order.getStatus());
    }


}
