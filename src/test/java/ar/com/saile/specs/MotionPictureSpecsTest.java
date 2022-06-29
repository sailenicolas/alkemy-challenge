package ar.com.saile.specs;

import ar.com.saile.domain.MotionPicture;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

class MotionPictureSpecsTest {

    /**
     * Method under test: {@link MotionPictureSpecs#MotionPictureSpecs(Map)}
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
        Map<String, String> maps = Map.of("title", "algo");

        // Act
        MotionPictureSpecs actualMotionPictureSpecs = new MotionPictureSpecs( maps );

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link MotionPictureSpecs#toPredicate(Root, CriteriaQuery, CriteriaBuilder)}
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
        MotionPictureSpecs motionPictureSpecs = null;
        Root<MotionPicture> root = null;
        CriteriaQuery<?> query = null;
        CriteriaBuilder criteriaBuilder = null;

        // Act
        Predicate actualToPredicateResult = motionPictureSpecs.toPredicate( root, query, criteriaBuilder );

        // Assert
        // TODO: Add assertions on result
    }
}

