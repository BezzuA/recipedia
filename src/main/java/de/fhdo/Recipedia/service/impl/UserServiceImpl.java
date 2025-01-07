package de.fhdo.Recipedia.service.impl;

import de.fhdo.Recipedia.converter.UserConverter;
import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.entity.Challenge;
import de.fhdo.Recipedia.entity.User;
import de.fhdo.Recipedia.repository.ChallengeRepository;
import de.fhdo.Recipedia.repository.UserRepository;
import de.fhdo.Recipedia.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository,
                            ChallengeRepository challengeRepository,
                           UserConverter userConverter) {
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public UserDto login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return userConverter.toDto(user);
        }
        return null;
    }

    @Override
    @Transactional
    public UserDto register(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return null;
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        return userConverter.toDto(user);
    }

    @Override
    @Transactional
    public Boolean changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null || !user.getPassword().equals(oldPassword)) {
            return false;
        }

        user.setPassword(newPassword);
        userRepository.save(user);

        return true;
    }

    @Override
    @Transactional
    public Boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return false;
        }

        userRepository.delete(user);

        return true;
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        Long userId = userDto.getUserId();

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        String newName = userDto.getUsername();
        String newEmail = userDto.getEmail();

        if (newName != null && !newName.equals(user.getUsername())) {
            User existingUser = userRepository.findByUsername(newName);
            if (existingUser != null) {
                return null;
            }
        }

        if (newEmail != null && !newEmail.equals(user.getEmail())) {
            User existingUser = userRepository.findByEmail(newEmail);
            if (existingUser != null) {
                return null;
            }
        }

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setBio(userDto.getBio());
        userRepository.save(user);

        return userConverter.toDto(user);
    }

    @Override
    @Transactional
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        return userConverter.toDto(user);
    }

    @Override
    public List<UserDto> getUsersByChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);

        if (challenge == null) {
            return null;
        }

        return challenge.getUsers().stream().map(userConverter::toDto).toList();
    }
}
