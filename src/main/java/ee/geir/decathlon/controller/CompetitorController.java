package ee.geir.decathlon.controller;

import ee.geir.decathlon.entity.Competitor;
import ee.geir.decathlon.entity.Result;
import ee.geir.decathlon.repository.CompetitorRepository;
import ee.geir.decathlon.repository.ResultRepository;
import ee.geir.decathlon.service.CompetitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class CompetitorController {

    @Autowired
    private CompetitorRepository competitorRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private CompetitorService competitorService;


    @PostMapping("competitor")
    public List<Competitor> addCompetitor(@RequestBody Competitor competitor) {
        competitorService.validate(competitor);
        competitorRepository.save(competitor);
        return competitorRepository.findAll();
    }

    @GetMapping("competitor/{id}")
    public Competitor getCompetitor(@PathVariable Long id) {
        return competitorRepository.findById(id).get();
    }

    @GetMapping("competitor/{id}/results")
    public List<Result> getCompetitorResults(@PathVariable Long id) {
        return resultRepository.findByCompetitorId(id);
    }




    @PostMapping("competitors")
    public List<Competitor> addCompetitors(@RequestBody List<Competitor> competitors) {
        competitorService.validate(competitors);
        competitorRepository.saveAll(competitors);
        return competitorRepository.findAll();
    }

    @GetMapping("competitors")
    public List<Competitor> getCompetitors() {
        return competitorRepository.findAll();
    }

    @GetMapping("competitors/results")
    public List<Map<String, Object>> getCompetitorsWithResults() {
        return competitorService.buildLeaderboard();
    }



    @GetMapping("competitors/results/basic")
    public String getCompetitorsResults() {
        return competitorService.buildCompetitorsResults();
    }

}
