package org.example.kihelp.teacher.mapper.impl;

import org.example.kihelp.teacher.mapper.TeacherUpdateRequestToTeacherMapper;
import org.example.kihelp.teacher.model.Teacher;
import org.example.kihelp.teacher.model.request.TeacherUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class TeacherUpdateRequestToTeacherMapperImpl implements TeacherUpdateRequestToTeacherMapper {

    @Override
    public Teacher map(TeacherUpdateRequest request) {
        Teacher teacher = new Teacher();
        teacher.setName(request.name());
        return teacher;
    }
}
