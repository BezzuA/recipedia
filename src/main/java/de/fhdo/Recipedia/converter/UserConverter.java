package de.fhdo.Recipedia.converter;

import de.fhdo.Recipedia.entity.User;
import de.fhdo.Recipedia.dto.UserDto;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setBio(user.getBio());
        userDto.setRecipeIds(user.getRecipes().stream().map(recipe -> recipe.getRecipeId()).toList());
        userDto.setDiscussionIds(user.getDiscussions().stream().map(discussion -> discussion.getDiscussionId()).toList());
        userDto.setChallengeIds(user.getChallenges().stream().map(challenge -> challenge.getChallengeId()).toList());

        return userDto;
    }
}
