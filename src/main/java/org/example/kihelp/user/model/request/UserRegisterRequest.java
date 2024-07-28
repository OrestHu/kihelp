package org.example.kihelp.user.model.request;

public record UserRegisterRequest(
        String telegramId,
        String username
) {
}
