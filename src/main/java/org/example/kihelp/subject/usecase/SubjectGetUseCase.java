package org.example.kihelp.subject.usecase;

import org.example.kihelp.subject.model.resp.SubjectResponse;

import java.util.List;

public interface SubjectGetUseCase {
    List<SubjectResponse> findSubjects();
    SubjectResponse findSubject(Integer subjectId);
}
