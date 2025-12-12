package ee.geir.decathlon.service;

import ee.geir.decathlon.dto.ResultRequest;
import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.entity.Competitor;
import ee.geir.decathlon.entity.Result;
import ee.geir.decathlon.repository.CategoryRepository;
import ee.geir.decathlon.repository.CompetitorRepository;
import ee.geir.decathlon.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private CompetitorRepository competitorRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Result createResult(ResultRequest request) {
        Competitor competitor = competitorRepository.findByNameIgnoreCase(request.competitorName)
                .orElseThrow(() -> new RuntimeException("Competitor not found"));
        Category category = categoryRepository.findByNameIgnoreCase(request.categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        resultRepository.findByCompetitor_IdAndCategory_Id(competitor.getId(), category.getId())
                .ifPresent(r -> {
                    throw new RuntimeException("Result already exists for this competitor in this category.");
                });

        Result result = new Result(request.result, category, competitor);
        competitor.setTotalPoints(competitor.getTotalPoints() + result.getPoints());

        return resultRepository.save(result);
    }

}
