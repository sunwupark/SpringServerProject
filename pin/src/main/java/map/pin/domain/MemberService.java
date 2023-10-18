package map.pin.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository userRepository;

    public Optional<Member> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<Member> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Member findMemberByEmailAndProviderType(String email, ProviderType providerType) {
        return userRepository.findByEmailAndProviderType(email, providerType)
                .orElseThrow(() -> new RuntimeException("No member found with the given email and provider type"));
    }

    public Member save(Member member) {
        return userRepository.save(member);
    }

    @Transactional
    public String updateTokens(String email, String token, String refreshToken) {
        Optional<Member> memberOptional = userRepository.findByEmail(email);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return member.update(token, refreshToken);
        } else {
            throw new IllegalArgumentException("No member found with the given email");
        }
    }

}
