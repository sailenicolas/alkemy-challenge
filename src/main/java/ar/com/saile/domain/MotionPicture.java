package ar.com.saile.domain;

import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
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

    protected String image;

    protected String title;

    protected LocalDateTime created;

    protected Integer rating;
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Collection<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Collection<FictionalCharacter> fictionalCharacters = new ArrayList<>();

    public MotionPicture(String title, String image, LocalDateTime created, int rating) {
        this.created = created;
        this.title = title;
        this.image = image;
        this.rating = rating;

    }

    public void addFictionalCharacters(FictionalCharacter fictionalCharacters) {

        this.fictionalCharacters.add( fictionalCharacters );
    }

    public void addGenre(Genre genre) {

        this.genres.add(genre);
    }
}
