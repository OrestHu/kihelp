package org.example.kihelp.subject.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.mapper.SubjectRequestToSubjectMapper;
import org.example.kihelp.subject.model.request.SubjectRequest;
import org.example.kihelp.subject.service.SubjectService;
import org.example.kihelp.subject.usecase.SubjectCreateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectCreateUseCaseImpl implements SubjectCreateUseCase{
    private final SubjectRequestToSubjectMapper subjectRequestToSubjectMapper;
    private final SubjectService subjectService;

    @Override
    public void createSubject(SubjectRequest subjectRequest) {
        var subject = subjectRequestToSubjectMapper.map(subjectRequest);

        subjectService.createSubject(subject);
    }
}
