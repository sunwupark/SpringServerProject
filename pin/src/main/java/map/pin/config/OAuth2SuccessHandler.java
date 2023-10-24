package map.pin.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import map.pin.domain.Member;
import map.pin.domain.MemberService;
import map.pin.domain.ProviderType;
import map.pin.domain.RoleType;
import map.pin.service.Token;
import map.pin.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRequestMapper userRequestMapper;
    private final JwtUtil jwtUtil;
    private final MemberService  memberService;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);
        String email = oAuth2User.getAttribute("email");

        // 서비스 제공 플랫폼(GOOGLE, KAKAO, NAVER)이 어디인지 가져온다.
        String provider = oAuth2User.getAttribute("provider").toString();
        if (provider == null) {
            provider = "OTHER";  // 또는 실제로 사용할 수 있는 기본 제공자 이름
        } else {
            provider = provider.toUpperCase();
        };

        //access_token, refresh_token 가져오기
//        OAuth2AuthorizedClient authorizedClient =
//                this.authorizedClientService.loadAuthorizedClient(
//                        oAuth2User.getAttribute("registrationId"), ((OAuth2User) authentication.getPrincipal()).getName());
//
//        String accessToken  = authorizedClient.getAccessToken().getTokenValue();
//        String refreshToken = authorizedClient.getRefreshToken().getTokenValue();

        // CustomOAuth2UserService에서 셋팅한 로그인한 회원 존재 여부를 가져온다.
        boolean isExist = oAuth2User.getAttribute("exist");
        // OAuth2User로 부터 Role을 얻어온다.
        String role = oAuth2User.getAuthorities().stream().
                findFirst() // 첫번째 Role을 찾아온다.
                .orElseThrow(IllegalAccessError::new) // 존재하지 않을 시 예외를 던진다.
                .getAuthority(); // Role을 가져온다.

        if (isExist) {
            // 회원이 존재하면 jwt token 발행을 시작한다.
            Member member = memberService.findMemberByEmailAndProviderType(email, ProviderType.valueOf(provider));
            memberService.save(member);

            Token token = jwtUtil.generateToken(email, role);
            // accessToken을 쿼리스트링에 담는 url을 만들어준다.
            String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:8080/loginSuccess")
                    .queryParam("accessToken", token.getAccessToken())
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
            log.info("redirect 준비");
            // 로그인 확인 페이지로 리다이렉트 시킨다.
            getRedirectStrategy().sendRedirect(request, response, targetUrl);


        } else {
            Member newMember = Member.builder()
                    .email(email)
                    .roleType(RoleType.ADMIN)
                    .providerType(ProviderType.valueOf(provider))
                    .createdAt(LocalDateTime.now())
                    .build();
            // 회원 정보 저장
            memberService.save(newMember);
            // 회원이 존재하지 않을경우, 서비스 제공자와 email을 쿼리스트링으로 전달하는 url을 만들어준다.
            String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:8080/loginSuccess")
                    .queryParam("email", (String) oAuth2User.getAttribute("email"))
                    .queryParam("provider", provider)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
            // 회원가입 페이지로 리다이렉트 시킨다.
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
    }
}