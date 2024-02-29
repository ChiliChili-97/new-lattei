package com.sparta.project.icylattei.cart.service;


import static com.sparta.project.icylattei.user.entity.UserRoleEnum.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.sparta.project.icylattei.cart.dto.CartRequestDto;
import com.sparta.project.icylattei.cart.dto.CartResponseDto;
import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.cart.repository.CartRepository;
import com.sparta.project.icylattei.product.entity.Product;
import com.sparta.project.icylattei.product.repository.ProductRepository;
import com.sparta.project.icylattei.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

    @Test
    @DisplayName("장바구니 수정")
    void updateCartTest() {
        //given
        Long cartId = 1L;
        User user = new User("qwer12345", "qwer@12345", USER, "qwer12345");
        CartRequestDto cartRequestDto = new CartRequestDto(1L, 3);
        Product product = productRepository.findById(cartRequestDto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        Cart cart = new Cart(product, cartRequestDto, user, "장바구니");
        CartResponseDto before = new CartResponseDto(cart);
        CartService cartService = new CartService(cartRepository, productRepository);

        //when
        given(cartRepository.findById(cartId)).willReturn(Optional.of(cart));
        CartResponseDto after = cartService.updateCart(cartId, cartRequestDto, user);
        //then
        assertEquals(before.getId(), after.getId());
        assertEquals(before.getQuantity(), after.getQuantity());
        assertEquals(before.getProduct(), after.getProduct());
    }


}
