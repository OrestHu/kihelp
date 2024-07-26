package org.example.kihelp.subject.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.service.SubjectService;
import org.example.kihelp.subject.usecase.SubjectDeleteUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectDeleteUseCaseImpl implements SubjectDeleteUseCase {
    private final SubjectService subjectService;

    @Override
    public void deleteSubject(Integer subjectId) {
        subjectService.deleteSubjectById(subjectId);
    }
}
