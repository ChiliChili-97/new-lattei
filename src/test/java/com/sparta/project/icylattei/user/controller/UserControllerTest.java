package com.sparta.project.icylattei.user.controller;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.doNothing;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.project.icylattei.test.UserCommonTest;
import com.sparta.project.icylattei.user.service.UserService;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest implements UserCommonTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders         // webMvc Test를 하기 위해서 기본적으로 설정해줘야 하는 MvcBuilders
            .webAppContextSetup(context)  // context 정보를 setup해줘야 함.
            .build();

        // Mock 테스트 UserDetails 생성
        UserDetailsImpl testUserDetails = new UserDetailsImpl(TEST_USER);

        // SecurityContext 에 인증된 사용자 설정
        SecurityContextHolder.getContext()
            .setAuthentication(new UsernamePasswordAuthenticationToken(
                testUserDetails, testUserDetails.getPassword(), testUserDetails.getAuthorities()));
    }

    @DisplayName("회원가입 요청")
    @Test
    void signup() throws Exception {
        // given
        doNothing().when(userService).signup(TEST_USER_REQUEST_DTO); // doNothing : Mockito 라이브러리 사용, signup메서드 호출시 아무것도 하지 않는다는 뜻

        // when
        var action = mockMvc.perform(post("/users/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(TEST_USER_REQUEST_DTO)));

        // then
        action.andExpect(status().isOk());
        verify(userService, times(1)).signup(any());
    }

    @Disabled
    @DisplayName("로그아웃 요청")
    @Test
    void logout() throws Exception {
        // when
        var action = mockMvc.perform(post("/users/logout")
            .contentType(MediaType.APPLICATION_JSON));

        // then
        action.andExpect(status().isOk());
    }
}