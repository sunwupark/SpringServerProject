package mvp.io.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mvp.io.domain.Mission.MissionUserList;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String name;
    private String password;
    private String email;

    private String picture;

    private int point;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role; //ADMIN, MANAGER, USER

    private LocalDateTime birthdate;
    private String address;

    private String login_type="Google";

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE; // Assign the desired enum value here
    private Timestamp inactive_time = null;

    @CreatedDate
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updated_at;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<MissionUserList> orders = new ArrayList<>();

    @Builder
    public Users(String name, int point, LocalDateTime birthdate, String address, String password, String email, String login_type, String picture, Role role) {
        this.name = name;
        this.point = point;
        this.birthdate = birthdate;
        this.address = address;
        this.password = password;
        this.email = email;
        this.login_type = login_type;
        this.picture = picture;
        this.role = role;
    }

    public Users update(String name, String address, String picture){
        this.name = name;
        this.address = address;
        this.picture = picture;

        return this;
    }

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }

    public String getRoleKey(){
        return this.role.getKey();
    }



}

