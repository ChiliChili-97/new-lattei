package com.sparta.project.icylattei.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {

    private String productName;
    private int quantity;
    private String cartStatus;

    public CartRequestDto(String productName, int quantity, String cartStatus) {
        this.productName = productName;
        this.quantity = quantity;
        this.cartStatus = cartStatus;
    }
}
