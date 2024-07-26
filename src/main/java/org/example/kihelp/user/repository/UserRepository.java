package org.example.kihelp.user.repository;

import org.example.kihelp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByTelegramId(String telegramId);
    Optional<User> findByTelegramId(String telegramId);
}
