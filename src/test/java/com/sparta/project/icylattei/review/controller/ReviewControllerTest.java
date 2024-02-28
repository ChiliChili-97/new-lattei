package com.sparta.project.icylattei.review.controller;

import static com.sparta.project.icylattei.user.entity.UserRoleEnum.USER;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.project.icylattei.config.WebSecurityConfig;
import com.sparta.project.icylattei.filter.MockSpringSecurityFilter;
import com.sparta.project.icylattei.jwt.JwtAuthenticationFilter;
import com.sparta.project.icylattei.jwt.JwtUtil;
import com.sparta.project.icylattei.review.dto.requestDto.ReviewRequest;
import com.sparta.project.icylattei.review.service.ReviewService;
import com.sparta.project.icylattei.user.entity.User;
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
    controllers = {ReviewController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)

@MockBean(JpaMetamodelMappingContext.class)
public class ReviewControllerTest {

    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ReviewService reviewService;

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

        User user = new User("TEST_USER", "TEST_PASSWORD", USER, "TEST_NICKNAME");
        UserDetailsImpl testUserDetails = new UserDetailsImpl(user);

        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "password",
            testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("Create Review : 성공")
    void createReview() throws Exception {

        // given
        this.mockUserSetup();
        ReviewRequest request = new ReviewRequest("맛있습니다!!");

        String newReview = objectMapper.writeValueAsString(request);

        // when - then
        mvc.perform(post("/products/4/reviews")
                .content(newReview)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }
}
