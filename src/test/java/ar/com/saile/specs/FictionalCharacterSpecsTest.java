package ar.com.saile.specs;

import ar.com.saile.domain.FictionalCharacter;

import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class FictionalCharacterSpecsTest {

    /**
     * Method under test: {@link FictionalCharacterSpecs#FictionalCharacterSpecs(Map)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R005 Unable to load class.
        //   Class: org.threeten.bp.Instant
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        Map<String, String> search = null;

        // Act
        FictionalCharacterSpecs actualFictionalCharacterSpecs = new FictionalCharacterSpecs( search );

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link FictionalCharacterSpecs#toPredicate(Root, CriteriaQuery, CriteriaBuilder)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testToPredicate() {
        // TODO: Complete this test.
        //   Reason: R005 Unable to load class.
        //   Class: org.threeten.bp.Instant
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        FictionalCharacterSpecs fictionalCharacterSpecs = null;
        Root<FictionalCharacter> root = null;
        CriteriaQuery<?> query = null;
        CriteriaBuilder criteriaBuilder = null;

        // Act
        Predicate actualToPredicateResult = fictionalCharacterSpecs.toPredicate( root, query, criteriaBuilder );

        // Assert
        // TODO: Add assertions on result
    }
}

