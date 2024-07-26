package org.example.kihelp.user.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.mapper.UserToUserResponseMapper;
import org.example.kihelp.user.model.resp.UserResponse;
import org.example.kihelp.user.service.UserService;
import org.example.kihelp.user.usecase.UserGetUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserGetUseCaseImpl implements UserGetUseCase {
    private final UserService userService;
    private final UserToUserResponseMapper userToUserResponseMapper;

    @Override
    public List<UserResponse> getUsers() {
        var users = userService.getUsers();

        return users.stream().map(userToUserResponseMapper::map).toList();
    }

    @Override
    public UserResponse getUser(Long userId) {
        var user = userService.getUser(userId);

        return userToUserResponseMapper.map(user);
    }
}
