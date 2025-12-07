package ee.geir.decathlon.service;

import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.entity.Result;
import ee.geir.decathlon.repository.CategoryRepository;
import ee.geir.decathlon.repository.CompetitorRepository;
import ee.geir.decathlon.repository.ResultRepository;
import ee.geir.decathlon.util.Calculations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private CompetitorRepository competitorRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public Result createResult(double result, long categoryId, long competitorId) {
        if (!competitorRepository.existsById(competitorId)) {
            throw new RuntimeException("Competitor doesn't exist");
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("This category is not found"));

        int points = Calculations.calculatePoints(category.getName(), result);

        Result res = new Result(result, points, category, competitorId);
        return resultRepository.save(res);
    }

    public String getIndividualResults(long id) {

        return "";
        /*
        String output = "<table><tr><th>Competitor ID</th><th>Category</th><th>Result</th><th>Points</th></tr>";
        int points = 0;
        List<Result> competitorResults = resultRepository.findByCompetitorId(id);

        for (Result result : competitorResults) {
            output += result;
            points += result.getPoints();
        }
        output += "<tr><td>Total points:</td><td></td><td></td><td>" + points + "</td></tr></table>";
        return output;
         */
    }
}
