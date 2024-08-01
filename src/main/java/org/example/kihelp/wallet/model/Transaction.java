package org.example.kihelp.wallet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(schema = "ki_help", name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "payment_id", nullable = false)
    private String paymentId;
    @Column(name = "initial", nullable = false)
    private String initial;
    @Column(name = "amount", nullable = false)
    private Double amount;
    private Instant createdTimeStamp;

    @ManyToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;
}
