package org.example.kihelp.task.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.kihelp.user.model.User;

import java.time.Instant;

@Data
@Entity
@Table(schema = "ki_help", name = "histories")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Instant createdTimeStamp;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
}
