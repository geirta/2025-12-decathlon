package ee.geir.decathlon.controller;

import ee.geir.decathlon.dto.ResultRequest;
import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.entity.Competitor;
import ee.geir.decathlon.entity.Result;
import ee.geir.decathlon.repository.CategoryRepository;
import ee.geir.decathlon.repository.CompetitorRepository;
import ee.geir.decathlon.repository.ResultRepository;
import ee.geir.decathlon.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ResultService resultService;
    @Autowired
    private CompetitorRepository competitorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("result")
    public List<Result> addResult(@RequestBody ResultRequest request) {
        resultService.createResult(request);
        return resultRepository.findAll();
    }

    @GetMapping("names")
    public Map<String, List<String>> getNames() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("competitorNames", competitorRepository.findAll()
                .stream()
                .map(Competitor::getName)
                .toList());
        map.put("categoryNames", categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .toList());
        return map;
    }

}
