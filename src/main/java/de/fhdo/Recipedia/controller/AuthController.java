package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(
            path = "/register",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<UserDto> register(
            @RequestParam String username,
            @RequestParam String password) {
        UserDto userDto = userService.register(username, password);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(
            path = "/login",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<UserDto> login(
            @RequestParam String username,
            @RequestParam String password) {
        UserDto userDto = userService.login(username, password);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
