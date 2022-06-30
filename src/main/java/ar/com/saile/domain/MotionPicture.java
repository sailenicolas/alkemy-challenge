package ar.com.saile.domain;

import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class MotionPicture implements Serializable {

    static final String ID = "id";

    @JsonView(Views.SearchMotionPicture.class)
    @NotBlank
    @NotNull
    private String title;

    @JsonView(Views.SearchMotionPicture.class)
    private String image;


    @JsonView(Views.SearchMotionPicture.class)
    private LocalDateTime created = LocalDateTime.now();

    @NotBlank
    @NotNull
    private Integer rating;

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<FictionalCharacter> fictionalCharacters = new ArrayList<>();

    public MotionPicture(String title, String image, int rating) {

        this.title = title;
        this.image = image;
        this.rating = rating;
    }

    public void addFictionalCharacters(FictionalCharacter fictionalCharacters) {

        this.getFictionalCharacters().add( fictionalCharacters );
    }

    public void addGenre(Genre genre) {

        this.getGenres().add( genre );
    }

    public void deleteFictionalCharacters(FictionalCharacter fictionalCharacters) {

        this.getFictionalCharacters().remove( fictionalCharacters );
    }

    public void deleteGenre(Genre genre) {

        this.getGenres().remove( genre );
    }
}
