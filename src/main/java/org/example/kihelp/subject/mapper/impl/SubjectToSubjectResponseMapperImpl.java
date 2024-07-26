package org.example.kihelp.subject.mapper.impl;

import org.example.kihelp.subject.mapper.SubjectToSubjectResponseMapper;
import org.example.kihelp.subject.model.Subject;
import org.example.kihelp.subject.model.resp.SubjectResponse;
import org.springframework.stereotype.Component;

@Component
public class SubjectToSubjectResponseMapperImpl implements SubjectToSubjectResponseMapper {

    @Override
    public SubjectResponse map(Subject subject) {
        return new SubjectResponse(
                subject.getId(),
                subject.getTitle()
        );
    }
}
