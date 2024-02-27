package com.sparta.project.icylattei.cart.dto;

import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.order.entity.Order;
import com.sparta.project.icylattei.product.entity.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartResponseDto {

    private Long id;
    private Product product;
    private Integer quantity;


    public CartResponseDto(Cart cart) {
        this.id = cart.getCartId();
        this.product = cart.getProduct();
        this.quantity = cart.getQuantity();
    }
}
