package ee.geir.decathlon.entity;

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

//    @ManyToOne
//    private Competitor competitor;

    private long competitorId;

    public Result(double result, int points, Category category, long competitorId) {
        this.result = result;
        this.points = points;
        this.category = category;
        this.competitorId = competitorId;
    }

}
