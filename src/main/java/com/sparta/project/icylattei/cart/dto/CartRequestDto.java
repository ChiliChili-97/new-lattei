package com.sparta.project.icylattei.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {

    private Long productId;
    private int quantity;

    public CartRequestDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
