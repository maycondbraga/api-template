package com.md.apitemplate.facades;

import com.md.apitemplate.clients.DelayClient;
import com.md.apitemplate.dtos.responses.UserResponse;
import com.md.apitemplate.entities.User;
import com.md.apitemplate.mappers.UserMapper;
import com.md.apitemplate.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserFacade {

    private final UserService userService;
    private final DelayClient delayClient;
    private final UserMapper userMapper;

    public UserFacade(UserService userService, DelayClient delayClient, UserMapper userMapper) {
        this.userService = userService;
        this.delayClient = delayClient;
        this.userMapper = userMapper;
    }

    public List<UserResponse> getUsers() {
        List<User> users = userService.getUsers();
        List<UserResponse> usersResponse = userMapper.toUserResponseList(users);

        String result = delayClient.getDelay();
        log.info(result);

        return usersResponse;
    }
}
