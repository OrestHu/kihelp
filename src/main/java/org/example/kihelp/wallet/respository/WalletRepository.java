package org.example.kihelp.wallet.respository;

import org.example.kihelp.user.model.User;
import org.example.kihelp.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    boolean existsByUser(User user);
    Optional<Wallet> findByUser(User user);
    Optional<Wallet> findByUserId(Long userId);
}
