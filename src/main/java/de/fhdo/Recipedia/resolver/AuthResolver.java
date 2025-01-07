package de.fhdo.Recipedia.resolver;

import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.service.UserService;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;


@Controller
public class AuthResolver {
    private final UserService userService;

    public AuthResolver(UserService userService) {
        this.userService = userService;
    }

    @MutationMapping
    public UserDto register(@Argument String username, @Argument String password) {
        return userService.register(username, password);
    }

    @MutationMapping
    public UserDto login(@Argument String username, @Argument String password) {
        return userService.login(username, password);
    }
}
