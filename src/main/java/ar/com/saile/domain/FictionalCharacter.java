package ar.com.saile.domain;

import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @JsonView({Views.SearchCharacter.class})
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String history;

    @JsonView({Views.SearchCharacter.class})

    private String image;

    @NotBlank
    @NotNull
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

        this.getSeries().add( series );
    }

    public void deleteMotionPicture(MotionPicture series) {

        this.getSeries().remove( series );
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
