package com.errami.mics.orderservice.controller;


import com.errami.mics.orderservice.dto.OrderRequest;
import com.errami.mics.orderservice.dto.OrderResponse;
import com.errami.mics.orderservice.model.Order;
import com.errami.mics.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.placeOrder(orderRequest);
        return order.getId().toString();
    }

    @GetMapping("user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrdersByUserId(@PathVariable("userId") String userId) {
        return orderService.getAll_UserOrders(userId);
    }
    @GetMapping("{orderId}")
    public OrderResponse getOrder(@PathVariable("orderId") UUID orderId) {
        return orderService.get_OrderById(orderId);
    }
    /*
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(Principal principal) {
        String user_id=principal.getName();
        return orderService.getAll_UserOrders(user_id);
    }

     */

    @GetMapping("configuration/{config_id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderByConfiguration(@PathVariable("config_id") UUID config_id) {
        OrderResponse order= orderService.get_OrderByConfiguration(config_id);
        return order;
    }

}
