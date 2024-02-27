package com.sparta.project.icylattei.order.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class OrderHttpResponseDto {

    private final HttpStatus httpStatus;

}
