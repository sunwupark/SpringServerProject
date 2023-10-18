package map.pin.config;

import java.util.Date;
import map.pin.service.Token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours

    public String getAccessToken(Token token) {
        return token.getAccessToken();
    }

    public Token generateToken(String email, String role) {
        // Access token 생성
        String token = JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .sign(Algorithm.HMAC512(secret));

        // Refresh token 생성 (여기서는 간단하게 access token과 동일하게 만듭니다. 실제로는 다른 방식으로 만들어야 합니다.)
        String refreshToken = JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1500))
                .sign(Algorithm.HMAC512(secret));

        return new Token(token, refreshToken);
    }

    public String getAccountNameFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("accountName").asString();
    }
}
