package com.sparta.project.icylattei.cart.dto;

import com.sparta.project.icylattei.cart.entity.Cart;
import java.util.List;
import lombok.Getter;

@Getter
public class CartsResponseDto {

    List<CartResponseDto> carts;

    public CartsResponseDto(List<CartResponseDto> carts) {
        this.carts = carts;
    }
}
