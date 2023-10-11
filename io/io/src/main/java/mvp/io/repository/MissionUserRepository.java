package mvp.io.repository;

import mvp.io.domain.Mission.MissionUserList;
import mvp.io.domain.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionUserRepository extends JpaRepository<MissionUserList, Long> {
    public List<MissionUserList> findByUserId(Long userId);

}
