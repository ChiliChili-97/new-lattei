package com.sparta.project.icylattei.order.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {

    private List<Long> carts;

    public OrderRequestDto(List<Long> carts) {
        this.carts = carts;
    }
}
