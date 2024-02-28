package com.sparta.project.icylattei.order.service;

import com.sparta.project.icylattei.cart.dto.CartResponseDto;
import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.cart.repository.CartRepository;
import com.sparta.project.icylattei.order.dto.OrderRequestDto;
import com.sparta.project.icylattei.order.dto.OrderResponseDto;
import com.sparta.project.icylattei.order.dto.OrdersResponseDto;
import com.sparta.project.icylattei.order.entity.Order;
import com.sparta.project.icylattei.order.repository.OrderRepository;
import com.sparta.project.icylattei.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;


    public OrderResponseDto createOrder(OrderRequestDto requestDto, User user) {
        List<Cart> carts = new ArrayList<>();
        for (int i = 0; i < requestDto.getCarts().size(); i++) {
            carts.add(cartRepository.findById((long) requestDto.getCarts().get(i))
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다.")));
        }
        Order order = orderRepository.save(new Order(carts, user));
        List<CartResponseDto> cartResponseDtos = new ArrayList<>();
        for (Cart cart : carts) {
            cartResponseDtos.add(new CartResponseDto(cart));
        }
        return new OrderResponseDto(order, cartResponseDtos);
    }

    public OrdersResponseDto getOrders(User user) {
        List<Order> orders = orderRepository.findAllByUser(user);
        List<OrderResponseDto> responseDtos = new ArrayList<>();
        for (Order order : orders) {
            List<CartResponseDto> carts = new ArrayList<>();
            for (int j = 0; j < order.getCarts().size(); j++) {
                carts.add(new CartResponseDto(order.getCarts().get(j)));
            }
            responseDtos.add(new OrderResponseDto(order, carts));
        }
        return new OrdersResponseDto(responseDtos);
    }

    @Transactional
    public void updateOrder(Long orderId, User user, OrderRequestDto requestDto) {
        List<Cart> carts = new ArrayList<>();
        for (int i = 0; i < requestDto.getCarts().size(); i++) {
            carts.add(cartRepository.findById((long) requestDto.getCarts().get(i))
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다.")));
        }
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));
        if(!order.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("수정할 수 없습니다.");
        }
        order.update(carts);
    }


    public void deleteOrder(Long orderId, User user) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));
        if(!order.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("수정할 수 없습니다.");
        }
        orderRepository.delete(order);
    }
}
