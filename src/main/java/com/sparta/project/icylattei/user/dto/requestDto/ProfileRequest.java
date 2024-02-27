package com.sparta.project.icylattei.user.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileRequest {

    @Pattern(regexp = "^[a-z0-9]*${2,10}", message = "2~10자 사이로 입력해주세요.")
    @NotBlank(message = "닉네임은 필수로 입력해야 합니다.")
    private String nickname;

    @NotBlank(message = "내용은 필수로 입력해야 합니다.")
    private String info;

    public ProfileRequest(String nickname, String info) {
        this.nickname = nickname;
        this.info = info;
    }
}