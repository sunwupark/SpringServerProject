package mvp.io.repository;

import mvp.io.domain.Mission.MissionUserList;
import mvp.io.domain.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionUserRepository extends JpaRepository<MissionUserList, Long> {
    @Query("select u from MissionUserList u where u.mission_user.id = ?1")
    public List<MissionUserList> findByUser_mission(Long userId);

}
