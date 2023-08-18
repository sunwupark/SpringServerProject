package NoMetroWaiting.NMW;

import NoMetroWaiting.NMW.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends MongoRepository<Member,String> {
}
