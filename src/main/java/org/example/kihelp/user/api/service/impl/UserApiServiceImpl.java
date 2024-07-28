package org.example.kihelp.user.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.api.model.UserResponseApi;
import org.example.kihelp.user.api.service.UserApiService;
import org.example.kihelp.user.exception.UserNotFoundException;
import org.example.kihelp.user.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static org.example.kihelp.user.util.MessageError.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserApiServiceImpl implements UserApiService {
    private final UserService userService;

    @Override
    public UserResponseApi currentUserAccount() {
        var securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();

        if (authentication == null) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        var username = authentication.getName();
        var user = userService.getUserByTelegramId(username);

        return new UserResponseApi(user.getId());
    }
}
