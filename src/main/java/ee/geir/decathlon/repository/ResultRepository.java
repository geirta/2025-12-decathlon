package ee.geir.decathlon.repository;

import ee.geir.decathlon.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {

    List<Result> findByCompetitorId(long competitorId);



}
