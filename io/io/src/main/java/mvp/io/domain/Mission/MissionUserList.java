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
    private Mission mission_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users user_mission;

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

    public static MissionUserList createOrder(Users user, Mission mission) {
        MissionUserList missionUserList = new MissionUserList();
        missionUserList.setUser_mission(user);
        missionUserList.setMission_user(mission);

        return missionUserList;
    }

    @Override
    public String toString() {
        return "MissionUserList{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

}
