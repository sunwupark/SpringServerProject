package mvp.io.repository;

import mvp.io.domain.Mission.Mission;
import mvp.io.domain.Mission.MissionReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionReviewRepository extends JpaRepository<MissionReview, Long> {
    @Query("select u from MissionReview u where u.mission_reviews.id = ?1")
    public List<MissionReview> findByMission_Reviews_Id(Long mission_id);
    @Query("select u from MissionReview u where u.user_reviews.id = ?1")
    public List<MissionReview> findByUser_Reviews_Id(Long user_id);

}
