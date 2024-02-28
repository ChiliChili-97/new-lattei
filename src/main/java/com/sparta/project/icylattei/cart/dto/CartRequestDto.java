package com.sparta.project.icylattei.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {

    private Long productId;
    private int quantity;
    private String cartStatus;

    public CartRequestDto(Long productId, int quantity, String cartStatus) {
        this.productId = productId;
        this.quantity = quantity;
        this.cartStatus = cartStatus;
    }
}
