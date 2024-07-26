package org.example.kihelp.subject.service;

import org.example.kihelp.subject.model.Subject;

import java.util.List;

public interface SubjectService {
    void createSubject(Subject subject);
    List<Subject> findSubjects();
    Subject findSubject(Integer subjectId);
    void deleteSubjectById(Integer subjectId);
    void updateSubject(Integer subjectId, Subject updateSubject);
}
