package map.pin.domain;

import lombok.Getter;

@Getter
public class UserProfile {
    private final String oauthId;
    private final String name;
    private final String email;
    private final String imageUrl;

    public UserProfile(String oauthId, String name, String email, String imageUrl) {
        this.oauthId = oauthId;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public Member toMember() {
        return new Member(oauthId, name, email, imageUrl, Role.USER);
    }
}
