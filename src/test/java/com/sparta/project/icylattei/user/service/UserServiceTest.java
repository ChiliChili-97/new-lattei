package com.sparta.project.icylattei.user.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;

import com.sparta.project.icylattei.test.UserCommonTest;
import com.sparta.project.icylattei.test.UserTestUtils;
import com.sparta.project.icylattei.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest implements UserCommonTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @DisplayName("회원 가입")
    @Test
    void signup() {
        // given
        given(userRepository.findByUsername(anyString())).willReturn(
            Optional.empty());  // findByUserName 메서드가 호출되면, emty가 반환될거다. 즉, 중복된 사용자가 없다는 뜻
        given(passwordEncoder.encode(anyString())).willReturn(
            "encodedPassword");  // 비밀번호 정상적으로 인코딩 가정

        var testUser = UserTestUtils.get(TEST_USER);
        given(userRepository.save(any())).willReturn(testUser);   // 정상적으로 DB에 저장

        // when, then
        assertDoesNotThrow(
            () -> userService.signup(TEST_USER_REQUEST_DTO));  // 회원가입 성공시 어떠한 Exception도 던져지지 않는다.

        verify(userRepository, times(1)).findByUsername(
            anyString());  // findByUsername 메서드가 한번 호출되었는지 확인
        verify(userRepository, times(1)).save(any());
    }


    @DisplayName("회원 가입 실패 - 중복된 사용자")
    @Test
    void signup_fail_duplicateUser() {
        // given
        given(userRepository.findByUsername(anyString())).willReturn(Optional.of(TEST_USER));

        // when, then
        assertThrows(DuplicateKeyException.class, () -> userService.signup(TEST_USER_REQUEST_DTO));
    }
}

