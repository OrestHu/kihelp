package org.example.kihelp.task.repository;

import org.example.kihelp.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByTitle(String title);
    Optional<Task> findByTitle(String title);
}
