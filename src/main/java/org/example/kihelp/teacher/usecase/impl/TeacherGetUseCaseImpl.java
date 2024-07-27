package org.example.kihelp.teacher.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.teacher.mapper.TeacherToTeacherResponseMapper;
import org.example.kihelp.teacher.model.resp.TeacherResponse;
import org.example.kihelp.teacher.service.TeacherService;
import org.example.kihelp.teacher.usecase.TeacherGetUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherGetUseCaseImpl implements TeacherGetUseCase {
    private final TeacherService teacherService;
    private final TeacherToTeacherResponseMapper teacherToTeacherResponseMapper;

    @Override
    public TeacherResponse getTeacher(Integer teacherId) {
        var teacher = teacherService.getTeacher(teacherId);

        return teacherToTeacherResponseMapper.map(teacher);
    }

    @Override
    public List<TeacherResponse> getTeachers() {
        var teachers = teacherService.getTeachers();

        return teachers.stream().map(teacherToTeacherResponseMapper::map).toList();
    }
}
