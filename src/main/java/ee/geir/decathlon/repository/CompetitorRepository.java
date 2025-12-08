package ee.geir.decathlon.repository;

import ee.geir.decathlon.entity.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor,Long> {

    @Override
    boolean existsById(Long aLong);

    Optional<Competitor> findByNameIgnoreCase(String name);


}
