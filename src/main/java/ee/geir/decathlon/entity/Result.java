package ee.geir.decathlon.entity;

import ee.geir.decathlon.util.Calculations;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double result;
    private int points;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Competitor competitor;

    public Result(double result, Category category, Competitor competitor) {
        this.result = result;
        this.points = Calculations.calculatePoints(category.getName(), result);
        this.category = category;
        this.competitor = competitor;
    }


}
