package org.example.kihelp.teacher.service;

import org.example.kihelp.subject.model.Subject;
import org.example.kihelp.teacher.model.Teacher;

import java.util.List;

public interface TeacherService {
    void createTeacher(Teacher teacher);
    Teacher getTeacher(Integer teacherId);
    List<Teacher> getTeachers();
    List<Teacher> getTeacherBySubject(Subject subject);
    void deleteTeacher(Integer teacherId);
    void updateTeacher(Integer teacherId, Teacher teacherUpdate);
}
