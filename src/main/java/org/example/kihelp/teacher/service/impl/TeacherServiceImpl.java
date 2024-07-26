package org.example.kihelp.teacher.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.teacher.model.Teacher;
import org.example.kihelp.teacher.repository.TeacherRepository;
import org.example.kihelp.teacher.service.TeacherService;
import org.springframework.stereotype.Service;

import static org.example.kihelp.teacher.util.MessageError.TEACHER_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public void createTeacher(Teacher teacher) {
        var exist = teacherRepository.existsBySubject(teacher.getSubject());

        if (exist){
            throw new RuntimeException(
                    String.format(TEACHER_ALREADY_EXIST, teacher.getName())
            );
        }
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacher(Integer teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(
                () -> new RuntimeException("Not")
        );
    }
}
