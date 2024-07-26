package org.example.kihelp.subject.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.mapper.SubjectRequestToSubjectMapper;
import org.example.kihelp.subject.model.request.SubjectRequest;
import org.example.kihelp.subject.service.SubjectService;
import org.example.kihelp.subject.usecase.SubjectUpdateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectUpdateUseCaseImpl implements SubjectUpdateUseCase {
    private final SubjectService subjectService;
    private final SubjectRequestToSubjectMapper subjectRequestToSubjectMapper;

    @Override
    public void updateSubject(Integer subjectId, SubjectRequest subjectRequest) {
        var subject = subjectRequestToSubjectMapper.map(subjectRequest);

        subjectService.updateSubject(subjectId, subject);
    }
}
