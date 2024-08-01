package org.example.kihelp.user.api.service;

import org.example.kihelp.user.api.model.UserResponseApi;

public interface UserApiService {
    UserResponseApi currentUserAccount();
    UserResponseApi currentUserAccountByTelegramId(String telegramId);
}
