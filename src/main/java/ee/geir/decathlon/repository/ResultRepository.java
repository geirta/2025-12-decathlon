package ee.geir.decathlon.repository;

import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.entity.Competitor;
import ee.geir.decathlon.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {

    List<Result> findByCompetitorId(long competitorId);

    Optional<Result> findByCompetitorAndCategory(Long competitorId, Long categoryId);

    Optional<Result> findByCompetitor_IdAndCategory_Id(Long id, Long id1);


}
