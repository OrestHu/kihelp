package org.example.kihelp.teacher.service;

import org.example.kihelp.teacher.model.Teacher;

import java.util.List;

public interface TeacherService {
    void createTeacher(Teacher teacher);
    Teacher getTeacher(Integer teacherId);
    List<Teacher> getTeachers();
    void deleteTeacher(Integer teacherId);
    void updateTeacher(Integer teacherId, Teacher teacherUpdate);
}
