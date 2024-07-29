package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.task.TaskDeleteUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskDeleteUseCaseImpl implements TaskDeleteUseCase {
    private final TaskService taskService;

    @Override
    public void deleteTask(Integer taskId) {
        taskService.deleteTask(taskId);
    }
}
