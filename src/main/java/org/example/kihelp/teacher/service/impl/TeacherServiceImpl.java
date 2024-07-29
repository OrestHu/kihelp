package org.example.kihelp.teacher.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.model.Subject;
import org.example.kihelp.teacher.exception.TeacherAlreadyExist;
import org.example.kihelp.teacher.exception.TeacherNotFoundException;
import org.example.kihelp.teacher.model.Teacher;
import org.example.kihelp.teacher.repository.TeacherRepository;
import org.example.kihelp.teacher.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.kihelp.teacher.util.MessageError.TEACHER_ALREADY_EXIST;
import static org.example.kihelp.teacher.util.MessageError.TEACHER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public void createTeacher(Teacher teacher) {
        var exist = teacherRepository.existsByNameAndSubject(teacher.getName(), teacher.getSubject());

        if (exist) {
            throw new TeacherAlreadyExist(String.format(TEACHER_ALREADY_EXIST, teacher.getName()));
        }

        teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacher(Integer teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(
                () -> new TeacherNotFoundException(TEACHER_NOT_FOUND)
        );
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getTeacherBySubject(Subject subject) {
        return teacherRepository.findBySubject(subject);
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        var teacher =getTeacher(teacherId);

        teacherRepository.delete(teacher);
    }

    @Override
    public void updateTeacher(Integer teacherId, Teacher teacherUpdate) {
        var teacher = getTeacher(teacherId);

        if(teacherUpdate.getName() != null && !teacherUpdate.getName().isEmpty()) {
            teacher.setName(teacherUpdate.getName());
        }

        teacherRepository.save(teacher);
    }
}
