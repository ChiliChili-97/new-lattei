package com.sparta.project.icylattei.review.dto.responseDto;

import com.sparta.project.icylattei.review.entity.Review;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponse {

    private Long reviewId;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ReviewResponse(String nickname, Review review) {
        this.reviewId = review.getReviewId();
        this.content = review.getContent();
        this.nickname = nickname;
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
    }
}
