package com.sparta.project.icylattei.order.dto;

import com.sparta.project.icylattei.cart.entity.Cart;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {

    private List<Long> carts;
}
