package com.md.apitemplate.mappers;

import com.md.apitemplate.dtos.responses.UserResponse;
import com.md.apitemplate.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserResponse userResponse);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);
}
