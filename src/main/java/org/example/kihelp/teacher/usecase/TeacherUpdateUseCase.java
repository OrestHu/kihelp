package org.example.kihelp.teacher.usecase;

import org.example.kihelp.teacher.model.request.TeacherUpdateRequest;

public interface TeacherUpdateUseCase {
    void updateTeacher(Integer teacherId, TeacherUpdateRequest request);
}
