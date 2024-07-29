package org.example.kihelp.task.repository;

import org.example.kihelp.subject.model.Subject;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByTitleAndTeacher(String title, Teacher teacher);
    Optional<Task> findByTitle(String title);
    List<Task> findByTeacher(Teacher teacher);
}
