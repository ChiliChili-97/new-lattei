package com.sparta.project.icylattei.cart.dto;

import com.sparta.project.icylattei.product.entity.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {
    private String productName;
    private int quantity;
    private String cartStatus;

}
