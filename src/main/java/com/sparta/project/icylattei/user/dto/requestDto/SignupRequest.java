package com.sparta.project.icylattei.user.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message =  "아이디는 필수로 입력해야 합니다.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "아이디는 영어 소문자와, 숫자(0~9)로 구성되어야 하며, 4자 이상 10자 이하여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 영어 대문자 및 소문자, 숫자(0~9)로 구성되어야 하며, 8자 이상 15자 이하여야 합니다.")
    private String password;

    @NotBlank
    private boolean admin = false;

    @NotBlank
    private String adminToken = "";
}

