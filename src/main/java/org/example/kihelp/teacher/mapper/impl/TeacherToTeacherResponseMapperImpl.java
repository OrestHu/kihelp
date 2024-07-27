package org.example.kihelp.teacher.mapper.impl;

import org.example.kihelp.teacher.mapper.TeacherToTeacherResponseMapper;
import org.example.kihelp.teacher.model.Teacher;
import org.example.kihelp.teacher.model.resp.TeacherResponse;
import org.springframework.stereotype.Component;

@Component
public class TeacherToTeacherResponseMapperImpl implements TeacherToTeacherResponseMapper {

    @Override
    public TeacherResponse map(Teacher teacher) {
        return new TeacherResponse(
                teacher.getId(),
                teacher.getName()
        );
    }
}
