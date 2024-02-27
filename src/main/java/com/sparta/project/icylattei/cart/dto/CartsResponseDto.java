package com.sparta.project.icylattei.cart.dto;

import com.sparta.project.icylattei.cart.entity.Cart;
import java.util.List;
import lombok.Getter;

@Getter
public class CartsResponseDto {

    List<Cart> carts;

    public CartsResponseDto(List<Cart> carts) {
        this.carts = carts;
    }
}
