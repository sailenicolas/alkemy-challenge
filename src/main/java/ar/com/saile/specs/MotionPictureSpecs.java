package ar.com.saile.specs;

import ar.com.saile.domain.Genre;
import ar.com.saile.domain.Genre_;
import ar.com.saile.domain.MotionPicture;
import ar.com.saile.domain.MotionPicture_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MotionPictureSpecs implements Specification<MotionPicture> {


    private final String title;

    private final String genre;

    private final List<Predicate> predicates;

    public MotionPictureSpecs(Map<String, String> maps) {

        this.title = maps.getOrDefault( "title", "" );
        this.genre = maps.getOrDefault( "genre", "" );
        this.predicates = new ArrayList<>();

    }

    @Override
    public Predicate toPredicate(Root<MotionPicture> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (!this.title.isEmpty() && !this.title.isBlank()) {
            predicates.add( criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get( MotionPicture_.title )
                            ),
                            "%" + this.title.toLowerCase() + "%"
                    )
            );
        } else if (!this.genre.isBlank() && !this.genre.isEmpty()) {
            query.distinct( true );
            Join<MotionPicture, Genre> join = root.join( MotionPicture_.genres );
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    join.get( Genre_.name )
                            ),
                            "%" + this.genre.toLowerCase() + "%"
                    )
            );
        }
        return criteriaBuilder.or(
                predicates.toArray(
                        new Predicate[]{}
                )
        );
    }
}
