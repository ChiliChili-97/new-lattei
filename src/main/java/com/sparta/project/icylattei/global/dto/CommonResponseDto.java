package com.sparta.project.icylattei.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)  // null이 아닌 경우에만 JSON에 포함되도록 지정, 객체를 JSON으로 변환시, 값이 null인 필드는 무시, 값이 있는 필드만 JSON에 포함된다.
public class CommonResponseDto<T> {

    private Integer statusCode;
    private T data;

}
