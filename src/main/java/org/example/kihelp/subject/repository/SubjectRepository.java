package org.example.kihelp.subject.repository;

import org.example.kihelp.subject.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByTitle(String title);
}
