package org.example.kihelp.subject.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "ki_help", name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String title;
}
