package org.example.kihelp.teacher.usecase;

import jakarta.validation.Valid;
import org.example.kihelp.teacher.model.request.TeacherRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TeacherCreateUseCase {
    void createTeacher(@Valid TeacherRequest request);
}
