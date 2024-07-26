package org.example.kihelp.teacher.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.kihelp.subject.model.Subject;

@Data
@Entity
@Table(schema = "ki_help", name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;
}
