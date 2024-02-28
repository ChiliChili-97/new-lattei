package com.sparta.project.icylattei.review.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequest {

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    @Size(max = 500)
    private String content;
}
