package org.example.kihelp.subject.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.mapper.SubjectToSubjectResponseMapper;
import org.example.kihelp.subject.model.resp.SubjectResponse;
import org.example.kihelp.subject.service.SubjectService;
import org.example.kihelp.subject.usecase.SubjectGetUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubjectGetUseCaseImpl implements SubjectGetUseCase {
    private final SubjectService subjectService;
    private final SubjectToSubjectResponseMapper subjectToSubjectResponseMapper;

    @Override
    public List<SubjectResponse> findSubjects() {
        var subjects = subjectService.findSubjects();

        return subjects.stream().map(subjectToSubjectResponseMapper::map).toList();
    }

    @Override
    public SubjectResponse findSubject(Integer subjectId) {
        var subject = subjectService.findSubject(subjectId);

        return subjectToSubjectResponseMapper.map(subject);
    }
}
