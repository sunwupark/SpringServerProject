package NoMetroWaiting.NMW.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "share")
@Getter @Setter
public class Share {
    @Id
    @GeneratedValue
    @Column(name = "SHARE_ID")
    private int id;
    private String currentRoom;
    private String finalDestination;
    private String destination;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
