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

    public static Specification<MotionPicture> isLongTermCustomer(String str) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // TODO: FIX RISK OF INJECTION?
            predicates.add( criteriaBuilder.like(
                    criteriaBuilder.lower(
                            root.get( MotionPicture_.title )
                    ),
                    "%" + str.toLowerCase() + "%" ) );
            //check if the text value can be casted to long.
            //if it is possible, then add the check to the query
            try {
                long longValue = Long.parseLong( str );
                predicates.add( criteriaBuilder.equal( root.get( MotionPicture_.id ), longValue ) );
            } catch (NumberFormatException e) {
                //do nothing, the text is not long
            }

            //check if the text can be casted to boolean
            //if it is possible, then add the check to the query
            Boolean value = "true".equalsIgnoreCase( str ) ? Boolean.TRUE :
                    "false".equalsIgnoreCase( str ) ? Boolean.FALSE : null;
            if (value != null) {
                predicates.add( criteriaBuilder.equal( root.get( "isActive" ), value ) );
            }
            return criteriaBuilder.or( predicates.toArray( new Predicate[]{} ) );

        };
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
