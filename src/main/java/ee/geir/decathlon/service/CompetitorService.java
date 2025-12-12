package ee.geir.decathlon.service;

import ee.geir.decathlon.entity.Competitor;
import org.springframework.stereotype.Service;

@Service
public class CompetitorService {

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
