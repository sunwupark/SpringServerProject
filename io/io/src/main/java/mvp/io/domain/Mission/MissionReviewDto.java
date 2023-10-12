package mvp.io.domain.Mission;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionReviewDto {

    private String body;
    private int rating;

    public MissionReview toEntity(){
        return MissionReview.builder()
                .body(body)
                .rating(rating)
                .build();
    }

    @Builder
    public MissionReviewDto(String body, int rating){
        this.body = body;
        this.rating = rating;
    }
}
