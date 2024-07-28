package org.example.kihelp.task.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.service.SubjectService;
import org.example.kihelp.task.mapper.TaskRequestToTaskMapper;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.req.TaskRequest;
import org.example.kihelp.task.service.ArgumentService;
import org.example.kihelp.teacher.service.TeacherService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskRequestToTaskMapperImpl implements TaskRequestToTaskMapper {
    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final ArgumentService argumentService;

    @Override
    public Task map(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.title());
        task.setPath(taskRequest.path());
        task.setPrice(taskRequest.price());
        task.setDiscount(0.0);

        var subject = subjectService.findSubject(taskRequest.subjectId());
        var arguments = taskRequest.argumentsId().stream().map(argumentService::getArgument).toList();
        var teacher = teacherService.getTeacher(taskRequest.teacherId());

        task.setSubject(subject);
        task.setTeacher(teacher);
        task.setArguments(arguments);

        return task;
    }
}
