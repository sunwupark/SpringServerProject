package NoMetroWaiting.NMW;

import NoMetroWaiting.NMW.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends MongoRepository<User,String> {
}
