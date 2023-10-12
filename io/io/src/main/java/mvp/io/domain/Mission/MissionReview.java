package mvp.io.domain.Mission;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mvp.io.domain.User.Users;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class MissionReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_REVIEW_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission_reviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users user_reviews;

    private String body;

    private int rating;

    @CreatedDate
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }

    @Builder
    public MissionReview(String body, int rating){
        this.body = body;
        this.rating = rating;
    }

    public void update(String body, int rating){
        this.body = body;
        this.rating = rating;
    }
}
