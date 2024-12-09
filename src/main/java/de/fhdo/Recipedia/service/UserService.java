package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.model.User;
import de.fhdo.Recipedia.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserService {

	@Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Validate and encrypt password, ensure username/email are unique
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Authentication logic here
    public User authenticateUser(String username, String password) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found or password does not match"));
    }

    // Changing information of current users
    public User updateUserProfile(String userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setEmail(updatedUser.getEmail());
                    user.setUsername(updatedUser.getUsername());
                    user.setBio(updatedUser.getBio());
                    // More updates can be added here
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

