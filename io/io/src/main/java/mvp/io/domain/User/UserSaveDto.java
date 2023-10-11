package mvp.io.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserSaveDto {
    private String name;
    private int point;
    private LocalDateTime birthdate;
    private String address;
    private String picture;

    @Builder
    public UserSaveDto(String name, int point, LocalDateTime birthdate, String address, String picture) {
        this.name = name;
        this.point = point;
        this.birthdate = birthdate;
        this.address = address;
        this.picture = picture;
    }
    public Users toEntity() {
        return Users.builder()
                .name(name)
                .birthdate(birthdate)
                .address(address)
                .picture(picture)
                .build();
    }
}
