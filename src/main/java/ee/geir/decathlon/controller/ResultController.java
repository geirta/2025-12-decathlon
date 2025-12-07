package ee.geir.decathlon.controller;

import ee.geir.decathlon.entity.Competitor;
import ee.geir.decathlon.entity.Result;
import ee.geir.decathlon.repository.CompetitorRepository;
import ee.geir.decathlon.repository.ResultRepository;
import ee.geir.decathlon.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ResultService resultService;

    @PostMapping("result")
    public List<Result> addResult(@RequestBody Map<String, Object> body) {
        double resultValue = ((Number) body.get("result")).doubleValue();
        long categoryId = ((Number) body.get("categoryId")).longValue();
        long competitorId = ((Number) body.get("competitorId")).longValue();

        resultService.createResult(resultValue, categoryId, competitorId);
        return resultRepository.findAll();
    }

}
