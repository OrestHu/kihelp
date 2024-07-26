package org.example.kihelp.user.service;

import org.example.kihelp.user.model.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getUsers();
    User getUser(Long userId);
}
