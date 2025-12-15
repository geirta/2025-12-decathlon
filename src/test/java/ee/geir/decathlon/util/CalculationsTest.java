package ee.geir.decathlon.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CalculationsTest {

    // https://www.sportcalculators.com/decathlon-calculator

    @Test
    void calculate_100mSprint_Points() {
        double sum = Calculations.calculatePoints("100M_SPRINT", 10);
        assertEquals(1096, sum);
    }

    @Test
    void calculate_longJump_Points() {
        double sum = Calculations.calculatePoints("LONG_JUMP", 783);
        assertEquals(1017, sum);
    }


}