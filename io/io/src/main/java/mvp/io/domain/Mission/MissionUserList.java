package mvp.io.domain.Mission;

import jakarta.persistence.*;
import lombok.Data;
import mvp.io.domain.User.Users;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@Data
@Entity
public class MissionUserList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users user;


    @CreatedDate
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updated_at;

    public static MissionUserList createOrder(Users user, Mission mission) {
        MissionUserList missionUserList = new MissionUserList();
        missionUserList.setUser(user);
        missionUserList.setMission(mission);

        return missionUserList;
    }
}
