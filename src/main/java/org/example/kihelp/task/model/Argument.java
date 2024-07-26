package org.example.kihelp.task.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "ki_help", name = "arguments")
public class Argument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
