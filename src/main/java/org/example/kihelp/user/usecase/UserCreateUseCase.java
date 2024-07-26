package org.example.kihelp.user.usecase;

import org.example.kihelp.user.model.request.UserRegisterRequest;
import org.example.kihelp.user.model.resp.JwtUserResponse;

public interface UserCreateUseCase {
    JwtUserResponse createUser(UserRegisterRequest registerRequest);
}
