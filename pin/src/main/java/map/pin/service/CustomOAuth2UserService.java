package map.pin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import map.pin.domain.Member;
import map.pin.domain.MemberService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

            // AccessToken 출력
        System.out.println("Access Token Value: " + userRequest.getAccessToken().getTokenValue());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        var memberAttribute = oAuth2Attribute.convertToMap();

        String email = (String) memberAttribute.get("email");
        // 이메일로 가입된 회원인지 조회한다.
        Optional<Member> findMember = memberService.findByEmail(email);
        memberAttribute.put("accessToken", userRequest.getAccessToken().getTokenValue());


        if (findMember.isEmpty()) {
            // 회원이 존재하지 않을경우, memberAttribute의 exist 값을 false로 넣어준다.
            memberAttribute.put("exist", false);
            // 회원의 권한(회원이 존재하지 않으므로 기본권한인 ROLE_USER를 넣어준다), 회원속성, 속성이름을 이용해 DefaultOAuth2User 객체를 생성해 반환한다.
            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    memberAttribute, "email");
        }

        // 회원이 존재할경우, memberAttribute의 exist 값을 true로 넣어준다.
        memberAttribute.put("exist", true);

        // 회원의 권한과, 회원속성, 속성이름을 이용해 DefaultOAuth2User 객체를 생성해 반환한다.
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_".concat(findMember.get().getRoleType().toString()))),
                memberAttribute, "email");

    }
}
