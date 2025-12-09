package ee.geir.decathlon.LATER;

import ee.geir.decathlon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CompetitionRepository competitionRepository;

    public void validate(Competition competition) {
        if (competitionRepository.existsByNameIgnoreCase(competition.getName())) {
            throw new RuntimeException("This competition already exists.");
        }
        categoryService.validate(competition.getCategories());
    }

}
