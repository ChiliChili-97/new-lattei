package com.sparta.project.icylattei.user.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d].{4,10}$",
        message = "아이디는 최소 4자 이상, 10자 이하로 알파벳 소문자와 숫자로 구성되어야 합니다.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\W)(?=.*\\d).{8,15}$",
        message = "비밀번호는 최소 8자 이상, 15자 이하로 알파벳과 특수문자, 숫자로 구성되어야 합니다.")
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z가-힣\\d]{2,10}$",
        message = "닉네임은 2~10자로 구성되어야 하며, 숫자로만 구성될 수 없습니다.")
    private String nickname;

    private boolean admin = false;

    private String adminToken = "";
}

