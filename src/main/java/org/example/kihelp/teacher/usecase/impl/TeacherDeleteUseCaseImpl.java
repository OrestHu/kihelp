package org.example.kihelp.teacher.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.teacher.service.TeacherService;
import org.example.kihelp.teacher.usecase.TeacherDeleteUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherDeleteUseCaseImpl implements TeacherDeleteUseCase {
    private final TeacherService teacherService;

    @Override
    public void deleteTeacher(Integer teacherId) {
        teacherService.deleteTeacher(teacherId);
    }
}
