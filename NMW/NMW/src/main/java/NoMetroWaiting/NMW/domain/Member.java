package NoMetroWaiting.NMW.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//@Entity
@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name= "member_id")
    private Long id;
    private String name;

    private String nickname;

//    @OneToMany(mappedBy = "member")
//    private List<Share> share = new ArrayList<>();
}
