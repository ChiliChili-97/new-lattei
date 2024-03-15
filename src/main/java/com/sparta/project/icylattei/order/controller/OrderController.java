package com.sparta.project.icylattei.order.controller;

import com.sparta.project.icylattei.global.annotation.Logging;
import com.sparta.project.icylattei.order.dto.OrderRequestDto;
import com.sparta.project.icylattei.order.dto.OrderResponseDto;
import com.sparta.project.icylattei.order.dto.OrdersResponseDto;
import com.sparta.project.icylattei.order.service.OrderService;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/orders")
@Logging
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto createOrder(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody OrderRequestDto requestDto) {
        return orderService.createOrder(requestDto, userDetails.getUser());
    }


    @GetMapping
    public OrdersResponseDto getOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getOrders(userDetails.getUser());
    }
    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable Long orderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getOrder(orderId,userDetails.getUser());
    }

    @PutMapping("/{orderId}")
    public void updateOrder(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long orderId, @RequestBody OrderRequestDto requestDto) {
        orderService.updateOrder(orderId, userDetails.getUser(), requestDto);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long orderId) {
        orderService.deleteOrder(orderId, userDetails.getUser());

    }

}
