package org.example.kihelp.user.service;

import org.example.kihelp.user.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    User getUserByUserUUID(String userUUID);
    User getUserByTelegramId(String telegramId);
    List<User> getUsers();
    User getUser(Long userId);
}
