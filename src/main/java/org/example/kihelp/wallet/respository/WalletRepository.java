package org.example.kihelp.wallet.respository;

import org.example.kihelp.user.model.User;
import org.example.kihelp.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByUser(User user);
}
