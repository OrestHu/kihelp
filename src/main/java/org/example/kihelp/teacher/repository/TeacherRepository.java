package org.example.kihelp.teacher.repository;

import org.example.kihelp.subject.model.Subject;
import org.example.kihelp.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    boolean existsByNameAndSubject(String name, Subject subject);
    List<Teacher> findBySubject(Subject subject);
}
