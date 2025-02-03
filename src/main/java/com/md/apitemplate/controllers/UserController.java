package com.md.apitemplate.controllers;

import com.md.apitemplate.dtos.responses.UserResponse;
import com.md.apitemplate.facades.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        log.info("GET /users");
        List<UserResponse> response = userFacade.getUsers();
        return ResponseEntity.ok(response);
    }
}
