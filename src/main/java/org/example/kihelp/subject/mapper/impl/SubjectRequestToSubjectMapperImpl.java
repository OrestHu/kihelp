package org.example.kihelp.subject.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.mapper.SubjectRequestToSubjectMapper;
import org.example.kihelp.subject.model.Subject;
import org.example.kihelp.subject.model.request.SubjectRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectRequestToSubjectMapperImpl implements SubjectRequestToSubjectMapper {

    @Override
    public Subject map(SubjectRequest subjectRequest) {
        Subject subject = new Subject();
        subject.setTitle(subjectRequest.title());
        return subject;
    }
}
