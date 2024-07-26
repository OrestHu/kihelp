package org.example.kihelp.task.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.TaskRequestToTaskMapper;
import org.example.kihelp.task.model.req.TaskRequest;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.TaskCreateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskCreateUseCaseImpl implements TaskCreateUseCase {
    private final TaskService taskService;
    private final TaskRequestToTaskMapper taskRequestToTaskMapper;

    @Override
    public void createTask(TaskRequest taskRequest) {
        var task = taskRequestToTaskMapper.map(taskRequest);

        taskService.createTask(task);
    }
}
