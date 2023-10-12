package mvp.io.service;

import lombok.RequiredArgsConstructor;
import mvp.io.domain.Mission.MissionReview;
import mvp.io.domain.Mission.MissionReviewDto;
import mvp.io.repository.MissionReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionReviewService {
    private final MissionReviewRepository missionReviewRepository;

    @Transactional
    public Long save(MissionReviewDto missionReviewDto){
        return missionReviewRepository.save(missionReviewDto.toEntity()).getId();
    }

    @Transactional
    public Long update(MissionReviewDto missionReviewDto, Long Id){
        MissionReview findMission = missionReviewRepository.findById(Id)
            .orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다. id=" + Id));
        findMission.update(missionReviewDto.getBody(), missionReviewDto.getRating());
        return Id;
    }

    @Transactional
    public void delete(Long Id){
        MissionReview findMission = missionReviewRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다. id=" + Id));
        missionReviewRepository.delete(findMission);
    }

    public MissionReview findById(Long Id){
        MissionReview findMission = missionReviewRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션이 없습니다 id = " + Id));
        return findMission;
    }

    public List<MissionReview> findByUserId(Long Id){
        List<MissionReview> findMission = missionReviewRepository.findByUser_Reviews_Id(Id);
        return findMission;
    }

    public List<MissionReview> findByMissionId(Long Id){
        List<MissionReview> findMission = missionReviewRepository.findByMission_Reviews_Id(Id);
        return findMission;
    }

    public List<MissionReview> getAllMissionReviews() {
        return missionReviewRepository.findAll();
    }
}
