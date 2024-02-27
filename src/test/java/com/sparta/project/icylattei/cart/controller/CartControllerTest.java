package com.sparta.project.icylattei.cart.controller;

import static com.sparta.project.icylattei.user.entity.UserRoleEnum.USER;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.project.icylattei.cart.dto.CartRequestDto;
import com.sparta.project.icylattei.cart.service.CartService;
import com.sparta.project.icylattei.config.WebSecurityConfig;
import com.sparta.project.icylattei.filter.MockSpringSecurityFilter;
import com.sparta.project.icylattei.jwt.JwtAuthenticationFilter;
import com.sparta.project.icylattei.jwt.JwtUtil;
import com.sparta.project.icylattei.user.controller.UserController;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.service.UserService;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import java.security.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(
    controllers = {UserController.class, CartController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)

@MockBean(JpaMetamodelMappingContext.class)
public class CartControllerTest {

    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    CartService cartService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .addFilter(jwtAuthenticationFilter)
            .apply(springSecurity(new MockSpringSecurityFilter()))
            .build();
    }

    private void mockUserSetup() {
        // Mock 테스트 유저 생성
        User user = new User("qwer12345", "qwer@12345", USER, "닉네임");
        UserDetailsImpl testUserDetails = new UserDetailsImpl(user);
        //credentials을 NULL로 하면 에러가나서 password입력
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "password",
            testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("장바구니 상품 추가")
    void createCartTest() throws Exception {
        //give
        this.mockUserSetup();
        CartRequestDto requestDto = new CartRequestDto("아아", 3, "장바구니");

        String postInfo = objectMapper.writeValueAsString(requestDto);
        //when - then
        mvc.perform(post("/users/carts")
                .content(postInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }
}
