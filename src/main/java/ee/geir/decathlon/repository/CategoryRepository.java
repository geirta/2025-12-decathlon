package ee.geir.decathlon.repository;

import ee.geir.decathlon.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Category> findByNameIgnoreCase(String name);
    Category findByName(String name);


    @Override
    Optional<Category> findById(Long aLong);
}
