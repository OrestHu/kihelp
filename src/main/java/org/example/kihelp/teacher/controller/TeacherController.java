package org.example.kihelp.teacher.controller;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.teacher.model.request.TeacherRequest;
import org.example.kihelp.teacher.usecase.TeacherCreateUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherCreateUseCase teacherCreateUseCase;

    @PostMapping("/teacher")
    public void createTeacher(@RequestBody TeacherRequest request) {
        teacherCreateUseCase.createTeacher(request);
    }
}
