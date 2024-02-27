package com.sparta.project.icylattei.cart.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CartHttpResponseDto {
    private final HttpStatus httpStatus;

}
