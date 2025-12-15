package ee.geir.decathlon.service;

import ee.geir.decathlon.entity.Competitor;
import ee.geir.decathlon.repository.CompetitorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CompetitorServiceTest {

    // given_when_then
    // eeltingimused_misK2ivitatakse_misOnTulemus

    @Mock
    private CompetitorRepository competitorRepository;

    @InjectMocks
    private CompetitorService competitorService;

    private void mockSaveCompetitorToDb(Long id, String name, String country, int age) {
        Competitor competitor = new Competitor();
        competitor.setId(id);
        competitor.setName(name);
        competitor.setCountry(country);
        competitor.setAge(age);
        competitor.setTotalPoints(0);
        when(competitorRepository.findById(id)).thenReturn(Optional.of(competitor));
    }

    // TEST: COMPETITOR ADDED WITH ID
    @Test
    void givenCompetitorCantBeAdded_whenCompetitorAddedWithId_thenExceptionIsThrown() {
        mockSaveCompetitorToDb(1L, "Mees", "Estonia", 25);
        Competitor comp = competitorRepository.findById(1L).get();
        String msg = assertThrows(RuntimeException.class, () -> competitorService.validate(comp)).getMessage();
        assertEquals("Cannot sign competitor with ID", msg);
    }

    // TEST: COMPETITOR ADDED WITOUT NAME
    @Test
    void givenCompetitorCantBeAdded_whenCompetitorAddedWithoutName_thenExceptionIsThrown() {
        Competitor comp = new Competitor();
        comp.setCountry("Estonia");
        comp.setAge(30);
        String msg = assertThrows(RuntimeException.class, () -> competitorService.validate(comp)).getMessage();
        assertEquals("Competitor needs to have a name", msg);
    }

    // TEST: COMPETITOR ADDED WITOUT COUNTRY
    @Test
    void givenCompetitorCantBeAdded_whenCompetitorAddedWithoutCountry_thenExceptionIsThrown() {
        Competitor comp = new Competitor();
        comp.setName("Geir");
        comp.setAge(30);
        String msg = assertThrows(RuntimeException.class, () -> competitorService.validate(comp)).getMessage();
        assertEquals("Competitor needs to have a country", msg);
    }

    // TEST: COMPETITOR ADDED WITOUT AGE
    @Test
    void givenCompetitorCantBeAdded_whenCompetitorAddedWithoutAge_thenExceptionIsThrown() {
        Competitor comp = new Competitor();
        comp.setCountry("Estonia");
        comp.setName("Mees");
        String msg = assertThrows(RuntimeException.class, () -> competitorService.validate(comp)).getMessage();
        assertEquals("Competitor needs to have an age", msg);
    }

}