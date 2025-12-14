package ee.geir.decathlon.service;

import ee.geir.decathlon.dto.ResultRequest;
import ee.geir.decathlon.entity.Category;
import ee.geir.decathlon.entity.Competitor;
import ee.geir.decathlon.entity.Result;
import ee.geir.decathlon.repository.CategoryRepository;
import ee.geir.decathlon.repository.CompetitorRepository;
import ee.geir.decathlon.repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    // given_when_then
    // eeltingimused_misK2ivitatakse_misOnTulemus


    @Mock
    private ResultRepository resultRepository;

    @Mock
    private CompetitorRepository competitorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ResultService resultService;

    @BeforeEach
    void setUp() {
    }

    private void mockSaveCompetitorToDb(String name, String country, int age) {
        Competitor competitor = new Competitor();
        competitor.setName(name);
        competitor.setCountry(country);
        competitor.setAge(age);
        when(competitorRepository.findByNameIgnoreCase(name)).thenReturn(Optional.of(competitor));
    }

    @Test
    void givenCompetitorDoesntExist_whenCompetitorNotInDbAndResultIsAdded_thenThrowException() {
        ResultRequest res = new ResultRequest("Geir", "DISCUS", 68);
        String msg = assertThrows(RuntimeException.class, () -> resultService.createResult(res)).getMessage();
        assertEquals("Competitor not found", msg);
    }

    @Test
    void givenCategoryDoesntExist_whenResultIsAdded_thenThrowException() {
        mockSaveCompetitorToDb("Geir", "Estonia", 31);

        Category category = new Category();
        category.setName("110M_HURDLES");
        lenient().when(categoryRepository.findByNameIgnoreCase("110M_HURDLES")).thenReturn(Optional.of(category));

        ResultRequest res = new ResultRequest();
        res.setCompetitorName("Geir");
        res.setCategoryName("NON_EXISTENT_CATEGORY");
        res.setResult(59.33);

        String msg = assertThrows(RuntimeException.class, () -> resultService.createResult(res)).getMessage();
        assertEquals("Category not found", msg);
    }

    @Test
    void givenResultAlreadyExists_whenResultIsAdded_thenThrowException() {
        Competitor competitor = new Competitor();
        competitor.setName("Geir");
        competitor.setCountry("Estonia");
        competitor.setAge(31);
        lenient().when(competitorRepository.findByNameIgnoreCase("Geir")).thenReturn(Optional.of(competitor));

        Category category = new Category();
        category.setName("100M");
        lenient().when(categoryRepository.findByNameIgnoreCase("100M")).thenReturn(Optional.of(category));

        Result result = new Result();
        result.setResult(10);
        result.setPoints(1096);
        result.setCompetitor(competitor);
        result.setCategory(category);
        lenient().when(resultRepository.findByCompetitor_IdAndCategory_Id(competitor.getId(), category.getId())).thenReturn(Optional.of(result));

        ResultRequest resReq = new ResultRequest();
        resReq.setCompetitorName("Geir");
        resReq.setCategoryName("100M");
        resReq.setResult(11);

        String msg = assertThrows(RuntimeException.class, () -> resultService.createResult(resReq)).getMessage();
        assertEquals("Result already exists for this competitor in this category.", msg);
    }
}