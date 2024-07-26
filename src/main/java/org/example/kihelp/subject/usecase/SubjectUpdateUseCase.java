package org.example.kihelp.subject.usecase;

import org.example.kihelp.subject.model.request.SubjectRequest;

public interface SubjectUpdateUseCase {
    void updateSubject(Integer subjectId, SubjectRequest subjectRequest);
}
