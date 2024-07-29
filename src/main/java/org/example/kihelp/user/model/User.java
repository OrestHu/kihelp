package org.example.kihelp.user.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.kihelp.wallet.model.Wallet;

import java.util.List;

@Data
@Entity
@Table(schema = "ki_help", name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telegramId;
    private String username;
    private String password;

    @ManyToMany
    @JoinTable( schema = "ki_help",
                name = "users_roles",
                joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Wallet wallet;
}
