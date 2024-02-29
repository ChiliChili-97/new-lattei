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

    User user;

    @Test
    @DisplayName("Create Review : 성공")
    void createReviewSuccess() {

        // given
        final Long EXISTENT_PRODUCT_ID = 7L;
        final String CONTENT = "맛있습니다!";
        final Long EXISTENT_USER_ID = 2L;

        ReviewRequest request = new ReviewRequest(CONTENT);

        user = userRepository.findById(EXISTENT_USER_ID)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        // when
        ReviewResponse review = reviewService.createReview(EXISTENT_PRODUCT_ID, request, user);

        // then
        assertNotNull(review.getReviewId());
        assertEquals(CONTENT, review.getContent());
    }

    @Test
    @DisplayName("Create Review : 실패 - 존재하지 않는 상품에 대한 리뷰 생성 시도")
    void createReviewFailWithNonExistentProduct() {

        // given
        final Long NON_EXISTENT_PRODUCT_ID = 100L;
        final String CONTENT = "맛있습니다!";
        final Long EXISTENT_USER_ID = 2L;

        ReviewRequest request = new ReviewRequest(CONTENT);

        user = userRepository.findById(EXISTENT_USER_ID)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        // when - then
        assertThrows(IllegalArgumentException.class, () -> {
            reviewService.createReview(NON_EXISTENT_PRODUCT_ID, request, user);
        });
    }
}
