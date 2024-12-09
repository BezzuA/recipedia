package de.fhdo.Recipedia;

import de.fhdo.Recipedia.UserRepositoryTests;
import de.fhdo.Recipedia.RecipeRepositoryTests;
import de.fhdo.Recipedia.CommentRepositoryTests;
import de.fhdo.Recipedia.RatingRepositoryTests;
import de.fhdo.Recipedia.ChallengeRepositoryTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    UserRepositoryTests.class,
    RecipeRepositoryTests.class,
    CommentRepositoryTests.class,
    RatingRepositoryTests.class,
    ChallengeRepositoryTests.class
})
public class PersistenceTestsSuite {
    // This class remains empty. It is used only as a holder for the above annotations.
}

