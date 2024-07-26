package org.example.kihelp.user.usecase;

import org.example.kihelp.user.model.resp.UserResponse;

import java.util.List;

public interface UserGetUseCase {
    List<UserResponse> getUsers();
    UserResponse getUser(Long userId);
}
