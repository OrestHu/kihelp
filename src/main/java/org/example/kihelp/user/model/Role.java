package org.example.kihelp.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "ki_help", name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
