package org.example.kihelp.teacher.usecase;

import org.example.kihelp.teacher.model.resp.TeacherResponse;

import java.util.List;

public interface TeacherGetUseCase {
    TeacherResponse getTeacher(Integer teacherId);
    List<TeacherResponse> getTeacherBySubject(Integer subjectId);
    List<TeacherResponse> getTeachers();
}
