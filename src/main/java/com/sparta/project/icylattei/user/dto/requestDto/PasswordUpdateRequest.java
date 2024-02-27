package com.sparta.project.icylattei.user.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PasswordUpdateRequest {
    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\W)(?=.*\\d).{8,15}$")
    private String password;
}
