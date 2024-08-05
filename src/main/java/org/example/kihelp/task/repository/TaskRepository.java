package org.example.kihelp.task.repository;

import org.example.kihelp.task.model.Task;
import org.example.kihelp.teacher.model.Teacher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByTitleAndTeacher(String title, Teacher teacher);
    List<Task> findByTeacher(Teacher teacher, Sort sort);
}
