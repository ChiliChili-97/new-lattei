package com.sparta.project.icylattei.review.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sparta.project.icylattei.review.dto.requestDto.ReviewRequest;
import com.sparta.project.icylattei.review.dto.responseDto.ReviewResponse;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserRepository userRepository;

    ReviewResponse createReview = null;

    User user;

    @Test
    @DisplayName("Create Review : 성공")
    void createReviewSuccess() {

        // given
        Long productId = 7L;
        String content = "맛있습니다!";

        ReviewRequest request = new ReviewRequest(content);
        user = userRepository.findById(2L).orElse(null);

        // when
        ReviewResponse review = reviewService.createReview(productId, request, user);

        // then
        assertNotNull(review.getReviewId());
        assertEquals(content, review.getContent());
    }

    @Test
    @DisplayName("Create Review : 실패")
    void createReviewFail() {

        // given
        Long productId = 10L;
        String content = "맛있습니다!";

        ReviewRequest request = new ReviewRequest(content);
        user = userRepository.findById(2L).orElse(null);

        // when - then
        assertThrows(IllegalArgumentException.class, () -> {
            reviewService.createReview(productId, request, user);
        });
    }
}
