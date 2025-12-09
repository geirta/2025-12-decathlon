package ee.geir.decathlon.service;

import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.entity.Competitor;
import ee.geir.decathlon.entity.Result;
import ee.geir.decathlon.repository.CompetitorRepository;
import ee.geir.decathlon.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompetitorService {

    @Autowired
    private CompetitorRepository competitorRepository;
    @Autowired
    private ResultRepository resultRepository;

    public List<Map<String, Object>> buildLeaderboard() {
        List<Competitor> competitors = competitorRepository.findAll();
        List<Map<String, Object>> leaderboard = new ArrayList<>();

        for (Competitor comp : competitors) {
            List<Result> results = resultRepository.findByCompetitorId(comp.getId());
            int totalPoints = results.stream()
                    .mapToInt(Result::getPoints)
                    .sum();

            Map<String, Object> obj = new HashMap<>();
            obj.put("id", comp.getId());
            obj.put("name", comp.getName());
            obj.put("country", comp.getCountry());
            obj.put("age", comp.getAge());
            obj.put("totalPoints", totalPoints);

            leaderboard.add(obj);
        }
        leaderboard.sort((a,b) -> ((Integer)b.get("totalPoints")).compareTo((Integer)a.get("totalPoints")));
        return leaderboard;
    }

    public void validate(Competitor competitor) {
        if (competitor.getId() != null) {
            throw new RuntimeException("Cannot sign competitor with ID");
        }
        if (competitor.getAge() == null) {
            throw new RuntimeException("Competitor needs to have an age");
        }
        if (competitor.getCountry() == null) {
            throw new RuntimeException("Competitor needs to have a country");
        }
        if (competitor.getName() == null) {
            throw new RuntimeException("Competitor needs to have a name");
        }
    }

}
