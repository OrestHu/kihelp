package org.example.kihelp.task.repository;

import org.example.kihelp.task.model.Argument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArgumentRepository extends JpaRepository<Argument, Integer> {
    boolean existsByName(String name);
}
