package NoMetroWaiting.NMW.service;
import NoMetroWaiting.NMW.domain.Member;
import NoMetroWaiting.NMW.repository.MemberRepository;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    public List<String> getShelterList(String title) {
        System.out.println("test");
        return null;
    }
//    @PersistenceContext
//    private final MemberRepository memberRepository;
//
//    @Transactional
//    public Long join(Member member){
//        validateDuplicateMember(member);
//        memberRepository.save(member);
//        return member.getId();
//    }
//
//    private void validateDuplicateMember(Member member) {
//        List<Member> findMembers = memberRepository.findByName(member.getName());
//        if(!findMembers.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 회원");
//        }
//    }
//
//    public List<Member> findMembers(){
//        return memberRepository.findAll();
//    }
//
//    public Member findOne(Long memberId){
//        return memberRepository.findOne(memberId);
//    }
}
