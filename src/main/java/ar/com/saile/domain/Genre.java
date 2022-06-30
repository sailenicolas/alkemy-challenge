package ar.com.saile.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Genre implements Serializable {

    static final String ID = "id";

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false)
    private Long id;

    private String name;

    private String image;

    @ManyToMany(mappedBy = "genres")
    private Collection<MotionPicture> motionPictures = new ArrayList<>();

    public Genre(String name, String image) {

        this.image = image;
        this.name = name;
    }

    public void addMotionPicture(MotionPicture motionPicture) {

        this.getMotionPictures().add(motionPicture);
    }
    public void deleteMotionPicture(MotionPicture motionPicture) {
        this.getMotionPictures().remove(motionPicture);
    }


}
