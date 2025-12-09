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


    // SECOND VARIANT
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


    // FIRST VARIANT
    public String buildCompetitorsResults() {
        String output = "<table><tr><th>Name</th><th>Country</th><th>Age</th><th>Points</th></tr>";

        List<Competitor> allCompetitors = competitorRepository.findAll();
        for (Competitor comp : allCompetitors) {
            int points = 0;
            List<Result> competitorResult = resultRepository.findByCompetitorId(comp.getId());
            for (Result res : competitorResult) {
                points += res.getPoints();
            }
            output += "<tr><td>" + comp.getName()
                    + "</td><td>" + comp.getCountry()
                    + "</td><td>" + comp.getAge()
                    + "</td><td>" + points + "<td></tr>";
        }
        output += "</table>";
        return output;
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

    public void validate(List<Competitor> competitor) {
        for (Competitor comp : competitor) {
            if (comp.getId() != null) {
                throw new RuntimeException("Cannot sign competitor with ID");
            }
            if (comp.getAge() == null) {
                throw new RuntimeException("Competitor needs to have an age");
            }
            if (comp.getCountry() == null) {
                throw new RuntimeException("Competitor needs to have a country");
            }
            if (comp.getName() == null) {
                throw new RuntimeException("Competitor needs to have a name");
            }
        }
    }
}
