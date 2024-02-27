package com.sparta.project.icylattei.test;

import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;

public interface CommonTest {

    String ANOTHER_PREFIX = "another-";
    Long TEST_USER_ID = 1L;
    Long TEST_ANOTHER_USER_ID = 2L;

    UserRoleEnum USER_ROLE = UserRoleEnum.USER;
    String TEST_USER_NAME = "username";
    String TEST_WRONG_USER_NAME = "Invalid username";
    String TEST_USER_PASSWORD = "password";
    String TEST_WRONG_USER_PASSWORD = "Invalid password";

    String TEST_USER_NAME_MESSAGE = "아이디는 영어 소문자와, 숫자(0~9)로 구성되어야 하며, 4자 이상 10자 이하여야 합니다.";
    String TEST_USER_PASSWORD_MESSAGE = "비밀번호는 영어 대문자 및 소문자, 숫자(0~9)로 구성되어야 하며, 8자 이상 15자 이하여야 합니다.";

    String TOKEN = "test-token";
    String INVALID_TOKEN = "invalid-token";

    User TEST_USER = User.builder()
        .username(ANOTHER_PREFIX + TEST_USER_NAME)
        .password(ANOTHER_PREFIX + TEST_USER_PASSWORD)
        .role(UserRoleEnum.USER)
        .build();
}
