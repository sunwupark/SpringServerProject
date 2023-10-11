package mvp.io.repository;

import mvp.io.domain.Mission.MissionReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionReviewRepository extends JpaRepository<MissionReview, Long> {
}
