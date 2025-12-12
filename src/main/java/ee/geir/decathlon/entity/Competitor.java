package ee.geir.decathlon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Competitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private Integer age;
    private int totalPoints;

    public Competitor(Long id, String name, String country, Integer age) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.age = age;
        this.totalPoints = 0;
    }

}
