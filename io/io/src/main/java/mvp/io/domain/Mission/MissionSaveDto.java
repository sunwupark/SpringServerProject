package mvp.io.domain.Mission;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mvp.io.domain.User.Users;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MissionSaveDto {
    private int point;
    private String content;

    @Builder
    public MissionSaveDto(String content, int point) {
        this.content = content;
        this.point = point;
    }
    public Mission toEntity() {
        return Mission.builder()
                .content(content)
                .point(point)
                .build();
    }
}
