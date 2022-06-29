package ar.com.saile.specs;

import ar.com.saile.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FictionalCharacterSpecs implements Specification<FictionalCharacter> {
    private final String title;

    private final String age;

    private final List<Predicate> predicates;

    private final String movies;

    public FictionalCharacterSpecs(Map<String, String> search) {

        this.title = search.getOrDefault( "title", "" );
        this.movies = search.getOrDefault( "movies", "" );
        this.age = search.getOrDefault( "age", "" );
        this.predicates = new ArrayList<>();
    }

    @Override
    public Predicate toPredicate(Root<FictionalCharacter> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (!this.title.isEmpty() && !this.title.isBlank()) {
            //check if the text value can be casted to long.
            //if it is possible, then add the check to the query
            predicates.add( criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    root.get( FictionalCharacter_.name )
                            ),
                            "%" + this.title.toLowerCase() + "%"
                    )
            );
        } else if (!this.movies.isBlank() && !this.movies.isEmpty()) {
            query.distinct( true );
            Join<FictionalCharacter, MotionPicture> join = root.join( FictionalCharacter_.series );
            predicates.add(
                    criteriaBuilder.like(
                            criteriaBuilder.lower(
                                    join.get( MotionPicture_.title )
                            ),
                            "%" + this.movies.toLowerCase() + "%"
                    )
            );
        }else if (!this.age.isBlank() && !this.age.isEmpty()) {
            query.distinct( true );
            try {
                long longValue = Integer.parseInt( this.age );
                predicates.add( criteriaBuilder.equal( root.get( FictionalCharacter_.age ), longValue ) );
            } catch (NumberFormatException e) {
                //do nothing, the text is not long
            }
        }
        return criteriaBuilder.or(
                predicates.toArray(
                        new Predicate[]{}
                )
        );
    }
}
