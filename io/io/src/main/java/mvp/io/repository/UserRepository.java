package mvp.io.repository;

import mvp.io.domain.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    public Users findByName(String name);

    Optional<Users> findByEmail(String email);

}
