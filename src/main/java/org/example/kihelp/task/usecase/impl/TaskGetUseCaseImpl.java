package org.example.kihelp.task.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.TaskToTaskInfoResponseMapper;
import org.example.kihelp.task.model.resp.TaskInfoResponse;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.TaskGetUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskGetUseCaseImpl implements TaskGetUseCase {
    private final TaskService taskService;
    private final TaskToTaskInfoResponseMapper taskToTaskInfoResponseMapper;

    @Override
    public TaskInfoResponse getTaskInfo(Integer taskId) {
        var task = taskService.getTaskById(taskId);

        return taskToTaskInfoResponseMapper.map(task);
    }
}
