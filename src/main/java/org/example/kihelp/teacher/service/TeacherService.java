package org.example.kihelp.teacher.service;

import org.example.kihelp.teacher.model.Teacher;

public interface TeacherService {
    void createTeacher(Teacher teacher);
    Teacher getTeacher(Integer teacherId);
}
