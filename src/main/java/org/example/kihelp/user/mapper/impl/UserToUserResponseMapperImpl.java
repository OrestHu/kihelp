package org.example.kihelp.user.mapper.impl;

import org.example.kihelp.user.mapper.UserToUserResponseMapper;
import org.example.kihelp.user.model.User;
import org.example.kihelp.user.model.resp.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseMapperImpl implements UserToUserResponseMapper {

    @Override
    public UserResponse map(User user) {
        return new UserResponse(
                user.getId(),
                user.getTelegramId(),
                user.getUsername()
        );
    }
}
