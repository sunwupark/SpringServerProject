package mvp.io.domain.Mission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mvp.io.domain.User.Role;
import mvp.io.domain.User.UserStatus;
import mvp.io.domain.User.Users;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_ID")
    private Long id;

    @Enumerated
    private MissionStatus status=MissionStatus.ACTIVE;

    @JsonIgnore
    @OneToMany(mappedBy = "mission_user", cascade = CascadeType.ALL)
    private List<MissionUserList> missionUserLists = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "mission_reviews", cascade = CascadeType.ALL)
    private List<MissionReview> missionReviews = new ArrayList<>();

    private int point;
    @Builder
    public Mission(int point, String content) {
        this.point = point;
        this.content = content;
    }

    public Mission update(String content, int point){
        this.content = content;
        this.point = point;

        return this;
    }

    private String content;

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
}

