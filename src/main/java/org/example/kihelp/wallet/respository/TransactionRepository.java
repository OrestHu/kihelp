package org.example.kihelp.wallet.respository;

import org.example.kihelp.wallet.model.Transaction;
import org.example.kihelp.wallet.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    boolean existsByPaymentId(String paymentId);
    Page<Transaction> findAllByWallet(Wallet wallet, Pageable pageable);
    @Query("SELECT SUM(t.amount) FROM Transaction t")
    Double findTotalAmount();
}
