package ee.geir.decathlon.Later;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    boolean existsByNameIgnoreCase(String name);

}
