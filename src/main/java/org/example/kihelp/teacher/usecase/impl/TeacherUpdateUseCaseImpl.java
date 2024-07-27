package org.example.kihelp.teacher.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.teacher.mapper.TeacherUpdateRequestToTeacherMapper;
import org.example.kihelp.teacher.model.request.TeacherUpdateRequest;
import org.example.kihelp.teacher.service.TeacherService;
import org.example.kihelp.teacher.usecase.TeacherUpdateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherUpdateUseCaseImpl implements TeacherUpdateUseCase {
    private final TeacherService teacherService;
    private final TeacherUpdateRequestToTeacherMapper teacherUpdateRequestToTeacherMapper;

    @Override
    public void updateTeacher(Integer teacherId, TeacherUpdateRequest request) {
        var teacherUpdate = teacherUpdateRequestToTeacherMapper.map(request);

        teacherService.updateTeacher(teacherId, teacherUpdate);
    }
}
