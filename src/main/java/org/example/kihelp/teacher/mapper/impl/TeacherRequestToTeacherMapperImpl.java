package org.example.kihelp.teacher.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.service.SubjectService;
import org.example.kihelp.teacher.mapper.TeacherRequestToTeacherMapper;
import org.example.kihelp.teacher.model.Teacher;
import org.example.kihelp.teacher.model.request.TeacherRequest;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TeacherRequestToTeacherMapperImpl implements TeacherRequestToTeacherMapper {
    private final SubjectService subjectService;

    @Override
    public Teacher map(TeacherRequest request) {
        Teacher teacher = new Teacher();

        teacher.setName(request.name());

        var subject = subjectService.findSubject(request.subjectId());
        teacher.setSubject(subject);

        return teacher;
    }
}
