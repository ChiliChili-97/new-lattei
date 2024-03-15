package com.sparta.project.icylattei.cart.controller;


import com.sparta.project.icylattei.cart.dto.CartRequestDto;
import com.sparta.project.icylattei.cart.dto.CartResponseDto;
import com.sparta.project.icylattei.cart.dto.CartsResponseDto;
import com.sparta.project.icylattei.cart.service.CartService;
import com.sparta.project.icylattei.global.annotation.Logging;
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
@RequestMapping("/users/carts")
@Logging
public class CartController {

    private final CartService cartService;

    @PostMapping
    public CartResponseDto createCart(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody CartRequestDto requestDto) {
        return cartService.createCart(requestDto, userDetails.getUser());
    }

    @GetMapping
    public CartsResponseDto getCart(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartService.getCart(userDetails.getUser());
    }

    @PutMapping("/{cartId}")
    public void updateCart(@PathVariable Long cartId,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody CartRequestDto requestDto) {
        cartService.updateCart(cartId, requestDto, userDetails.getUser());;
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable Long cartId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cartService.deleteCart(cartId, userDetails.getUser());
    }
}
