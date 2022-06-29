package ar.com.saile.domain;

import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class FictionalCharacter implements Serializable {

    private final static String ID_COLUMN = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN, nullable = false)
    private Long id;
    @JsonView(Views.SearchCharacter.class)
    private String name;
    private String history;
    @JsonView(Views.SearchCharacter.class)
    private String image;
    private Integer age;
    private Integer weigh;

    @ManyToMany(mappedBy = "fictionalCharacters", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<MotionPicture> series;

    public FictionalCharacter(String name, String history, String image, Integer age, Integer weigh) {

        this.name = name;
        this.history = history;
        this.image = image;
        this.age = age;
        this.weigh = weigh;
    }

    public void addMotionPicture(MotionPicture series) {
        this.series.add(series);
    }

    @Override
    public String toString() {

        return "FictionalCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", history='" + history + '\'' +
                ", age=" + age +
                ", weigh=" + weigh +
                '}';
    }
}
