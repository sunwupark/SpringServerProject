package mvp.io.service;

import lombok.RequiredArgsConstructor;
import mvp.io.domain.Mission.Mission;
import mvp.io.domain.Mission.MissionSaveDto;
import mvp.io.domain.User.UserSaveDto;
import mvp.io.domain.User.Users;
import mvp.io.repository.MissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;

    @Transactional
    public Long save(MissionSaveDto mission){
        return missionRepository.save(mission.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, MissionSaveDto mission){
        Mission findMission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다. id=" + id));

        System.out.println("findMission.getPoint() = " + findMission.getPoint());
        findMission.update(mission.getContent(), mission.getPoint());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Mission findMission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다 id = " + id));
        missionRepository.delete(findMission);
    }

    public Mission findById(Long id){
        Mission findMission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("해당 미션이 없습니다. id=" + id)));
        return findMission;
    }

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

}
