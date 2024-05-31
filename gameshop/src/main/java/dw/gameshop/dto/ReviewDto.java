package dw.gameshop.dto;

import dw.gameshop.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReviewDto {
    private long gameId;
    private String gameName;
    private String userId;
    private int reviewPoint;
    private String reviewText;

    public ReviewDto toReviewDtoFromReview(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewPoint(review.getPoint());
        reviewDto.setReviewText(review.getReviewText());
        reviewDto.setGameId(review.getGameId().getId());
        reviewDto.setGameName(review.getGameId().getTitle());
        reviewDto.setUserId(review.getUserId().getUserId());
        return reviewDto;
    }
    //ReviewDTO를 Review 엔티티로 형변환하는 메서드
}
