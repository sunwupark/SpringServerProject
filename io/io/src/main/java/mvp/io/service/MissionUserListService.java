package mvp.io.service;

import lombok.RequiredArgsConstructor;
import mvp.io.domain.Mission.Mission;
import mvp.io.domain.Mission.MissionSaveDto;
import mvp.io.domain.Mission.MissionUserList;
import mvp.io.domain.User.Users;
import mvp.io.repository.MissionRepository;
import mvp.io.repository.MissionUserRepository;
import mvp.io.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionUserListService {
    private final MissionUserRepository missionUserRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long order(Long userId, Long misisonId){
        Users member = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다. id=" + userId));;
        Mission item = missionRepository.findById(misisonId)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없습니다. id=" + userId));;

        MissionUserList missionUserList = MissionUserList.createOrder(member, item);

        missionUserRepository.save(missionUserList);
        return missionUserList.getId();
    }

    @Transactional
    public List<MissionUserList> getMissionUserListById(Long Id){
        List<MissionUserList> missionUserRepositoryAll = missionUserRepository.findByUserId(Id);
        return missionUserRepositoryAll;
    }

}
