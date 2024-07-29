package org.example.kihelp.task.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.kihelp.subject.model.Subject;
import org.example.kihelp.teacher.model.Teacher;

import java.util.List;

@Data
@Entity
@Table(schema = "ki_help", name = "tasks", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "teacher_id"})
})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    private String path;
    private Integer price;
    private Double discount;
    private Boolean type;

    @ManyToMany
    @JoinTable(
            schema = "ki_help",
            name = "tasks_arguments",
            joinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "argument_id", referencedColumnName = "id")}
    )
    private List<Argument> arguments;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;
}
