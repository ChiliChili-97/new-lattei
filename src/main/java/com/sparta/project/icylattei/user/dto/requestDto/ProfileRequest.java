package com.sparta.project.icylattei.user.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {

    @NotBlank(message = "닉네임은 필수로 입력해야 합니다.")
    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z가-힣\\d]{2,10}$",
        message = "닉네임은 2~10자로 구성되어야 하며, 숫자로만 구성될 수 없습니다.")
    private String nickname;

    @NotBlank(message = "내용은 필수로 입력해야 합니다.")
    private String info;
}