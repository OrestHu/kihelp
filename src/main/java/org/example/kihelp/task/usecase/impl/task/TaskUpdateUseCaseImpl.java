package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.TaskUpdateRequest;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.task.TaskUpdateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskUpdateUseCaseImpl implements TaskUpdateUseCase {
    private final TaskService taskService;

    @Override
    public void updateTask(Integer taskId, TaskUpdateRequest request) {
        taskService.updateTask(taskId, request);
    }
}
