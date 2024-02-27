package com.sparta.project.icylattei.cart.service;

import com.sparta.project.icylattei.cart.dto.CartRequestDto;
import com.sparta.project.icylattei.cart.dto.CartResponseDto;
import com.sparta.project.icylattei.cart.dto.CartsResponseDto;
import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.cart.repository.CartRepository;
import com.sparta.project.icylattei.product.entity.Product;
import com.sparta.project.icylattei.product.repository.ProductRepository;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.repository.UserRepository;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartResponseDto createCart(CartRequestDto requestDto, User user) {
        String productName = requestDto.getProductName();
        Product product = productRepository.findByProductName(productName);
        Cart cart = cartRepository.save(new Cart(product, requestDto, user));
        return new CartResponseDto(cart);
    }

    public CartsResponseDto getCart(User user) {
        List<Cart> cart = cartRepository.findAllByUserAndCartStatus(user, "장바구니");
        return new CartsResponseDto(cart);
    }

    public CartResponseDto deleteCart(Long cartId, User user) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));
        cartRepository.delete(cart);
        return new CartResponseDto(cart);
    }
@Transactional
    public CartResponseDto updateCart(Long cartId, CartRequestDto requestDto, User user) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));

        String productName = requestDto.getProductName();
        Product product = productRepository.findByProductName(productName);
        cart.update(product, requestDto);
        return new CartResponseDto(cart);
    }
}
