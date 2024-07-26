package org.example.kihelp.teacher.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.teacher.mapper.TeacherRequestToTeacherMapper;
import org.example.kihelp.teacher.model.request.TeacherRequest;
import org.example.kihelp.teacher.service.TeacherService;
import org.example.kihelp.teacher.usecase.TeacherCreateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherCreateUseCaseImpl implements TeacherCreateUseCase {
    private final TeacherService teacherService;
    private final TeacherRequestToTeacherMapper teacherRequestToTeacherMapper;

    @Override
    public void createTeacher(TeacherRequest request) {
        var teacher = teacherRequestToTeacherMapper.map(request);

        teacherService.createTeacher(teacher);
    }
}
