package org.example.kihelp.task.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.subject.service.SubjectService;
import org.example.kihelp.task.mapper.TaskToTaskInfoResponseMapper;
import org.example.kihelp.task.mapper.TaskToTaskResponseMapper;
import org.example.kihelp.task.model.resp.TaskInfoResponse;
import org.example.kihelp.task.model.resp.TaskResponse;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.TaskGetUseCase;
import org.example.kihelp.teacher.service.TeacherService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskGetUseCaseImpl implements TaskGetUseCase {
    private final TaskService taskService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final TaskToTaskInfoResponseMapper taskToTaskInfoResponseMapper;
    private final TaskToTaskResponseMapper taskToTaskResponseMapper;

    @Override
    public TaskInfoResponse getTaskInfo(Integer taskId) {
        var task = taskService.getTaskById(taskId);

        return taskToTaskInfoResponseMapper.map(task);
    }

    @Override
    public List<TaskResponse> getTasksBySubjectAndTeacher(Integer subjectId, Integer teacherId) {
        var teacher = teacherService.getTeacher(teacherId);
        var subject = subjectService.findSubject(subjectId);
        var tasks = taskService.getTasksBySubjectAndTeacher(subject, teacher);

        return tasks.stream().map(taskToTaskResponseMapper::map).toList();
    }
}
