package com.sparta.project.icylattei.order.dto;

import com.sparta.project.icylattei.order.entity.Order;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrdersResponseDto {

    List<OrderResponseDto> orders;

    public OrdersResponseDto(List<OrderResponseDto> orders) {
        this.orders = orders;
    }
}
