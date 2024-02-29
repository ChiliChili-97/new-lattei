package com.sparta.project.icylattei.user.dto.responseDto;


import com.sparta.project.icylattei.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProfileResponse {

    private String nickname;
    private String info;

    public ProfileResponse(User user) {
        this.nickname = user.getNickname();
        this.info = user.getInfo();
    }
}