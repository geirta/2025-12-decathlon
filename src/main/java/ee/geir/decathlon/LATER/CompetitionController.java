package ee.geir.decathlon.LATER;

import ee.geir.decathlon.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompetitionController {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("competition")
    public List<Competition> addCompetition(@RequestBody Competition competition) {
        //competitionService.validate(competition);
        categoryRepository.saveAll(competition.getCategories());
        competitionRepository.save(competition);

        return competitionRepository.findAll();
    }

    @GetMapping("competition/{id}")
    public Competition getCompetition(@PathVariable Long id) {
        return competitionRepository.findById(id).get();
    }

    @GetMapping("competitions")
    public List<Competition> getCompetitions() {
        return competitionRepository.findAll();
    }
}
