package org.example.kihelp.teacher.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kihelp.teacher.model.request.TeacherRequest;
import org.example.kihelp.teacher.model.request.TeacherUpdateRequest;
import org.example.kihelp.teacher.model.resp.TeacherResponse;
import org.example.kihelp.teacher.usecase.TeacherCreateUseCase;
import org.example.kihelp.teacher.usecase.TeacherDeleteUseCase;
import org.example.kihelp.teacher.usecase.TeacherGetUseCase;
import org.example.kihelp.teacher.usecase.TeacherUpdateUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherCreateUseCase teacherCreateUseCase;
    private final TeacherGetUseCase teacherGetUseCase;
    private final TeacherDeleteUseCase teacherDeleteUseCase;
    private final TeacherUpdateUseCase teacherUpdateUseCase;

    @PostMapping("/teacher")
    public void createTeacher(@Valid @RequestBody TeacherRequest request) {
        teacherCreateUseCase.createTeacher(request);
    }

    @GetMapping("/teacher/{teacher_id}")
    public TeacherResponse getTeacher(@PathVariable("teacher_id") Integer teacherId){
       return teacherGetUseCase.getTeacher(teacherId);
    }

    @GetMapping("/teacher/subject/{subject_id}")
    public List<TeacherResponse> getTeacherBySubject(@PathVariable("subject_id") Integer subjectId){
        return teacherGetUseCase.getTeacherBySubject(subjectId);
    }

    @GetMapping("/teacher")
    public List<TeacherResponse> getTeachers(){
        return teacherGetUseCase.getTeachers();
    }

    @DeleteMapping("/teacher/{teacher_id}")
    public void deleteTeacher(@PathVariable("teacher_id") Integer teacherId){
        teacherDeleteUseCase.deleteTeacher(teacherId);
    }

    @PutMapping("/teacher/{teacher_id}")
    public void updateTeacher(@PathVariable("teacher_id") Integer teacherId,
                              @Valid @RequestBody TeacherUpdateRequest request) {
        teacherUpdateUseCase.updateTeacher(teacherId, request);
    }
}
