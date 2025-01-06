package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.UserDto;

public interface UserService {
    UserDto login(String username, String password);
    UserDto register(String username, String password);
    Boolean changePassword(Long userId, String oldPassword, String newPassword);
    Boolean deleteUser(Long userId);
    UserDto updateUser(UserDto user);
}
