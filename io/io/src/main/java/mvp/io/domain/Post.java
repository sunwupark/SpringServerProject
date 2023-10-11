package mvp.io.domain;

import jakarta.persistence.*;
import mvp.io.domain.Mission.Mission;
import mvp.io.domain.User.Users;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MISSION_REVIEW_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission_id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users user_id;

//    @CreatedDate
//    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime created_at;
//
//    @LastModifiedDate
//    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime updated_at;

}
