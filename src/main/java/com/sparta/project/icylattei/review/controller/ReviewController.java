package com.sparta.project.icylattei.review.controller;

import com.sparta.project.icylattei.review.dto.requestDto.ReviewRequest;
import com.sparta.project.icylattei.review.dto.responseDto.ReviewResponse;
import com.sparta.project.icylattei.review.service.ReviewService;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/{productId}")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/reviews")
    public ReviewResponse createReview(@PathVariable Long productId,
        @RequestBody @Valid ReviewRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return reviewService.createReview(productId, request, userDetails.getUser());
    }

    // 상품별 리뷰 조회
    @GetMapping("/reviews")
    public List<ReviewResponse> LoadReviewList(@PathVariable Long productId) {

        return reviewService.LoadReviewList(productId);
    }

    // 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable Long productId, @PathVariable Long reviewId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewService.deleteReview(productId, reviewId, userDetails.getUser());
    }

    // 리뷰 수정
    @PutMapping("/reviews/{reviewId}")
    public ReviewResponse updateReview(@PathVariable Long productId, @PathVariable Long reviewId,
        @RequestBody @Valid ReviewRequest request,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return reviewService.updateReview(productId, reviewId, request, userDetails.getUser());
    }
}
