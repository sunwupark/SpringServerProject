package NoMetroWaiting.NMW.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private int id;
    private String name;
    private String nickname;
}
