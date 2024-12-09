package de.fhdo.Recipedia;

import de.fhdo.Recipedia.model.*;
import de.fhdo.Recipedia.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

@SpringBootTest
public class ChallengeRepositoryTests {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testCreateAndFindChallenge() {
        // Create and save Users
        User user1 = new User();
        user1.setUserId("5");
        user1.setUsername("participant1");
        user1.setEmail("part1@example.com");
        user1.setPassword("pass1");
        user1.setBio("Loves challenges.");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUserId("6");
        user2.setUsername("participant2");
        user2.setEmail("part2@example.com");
        user2.setPassword("pass2");
        user2.setBio("Enjoys cooking.");
        userRepository.save(user2);

        // Create and save a Challenge
        Challenge challenge = new Challenge();
        challenge.setChallengeId("2");
        challenge.setTitle("Baking Bonanza");
        challenge.setDescription("Bake the best cake.");
        challenge.setStartDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        challenge.setEndDate(cal.getTime());
        challenge.setUsers(Arrays.asList(user1, user2));
        challengeRepository.save(challenge);

        // Retrieve the Challenge
        Challenge foundChallenge = challengeRepository.findById("2").orElse(null);

        // Assertions
        assert foundChallenge != null;
        assert foundChallenge.getTitle().equals("Baking Bonanza");
        assert foundChallenge.getUsers().size() == 2;
    }
}
