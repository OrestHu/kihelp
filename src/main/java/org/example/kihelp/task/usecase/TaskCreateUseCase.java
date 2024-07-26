package org.example.kihelp.task.usecase;

import org.example.kihelp.task.model.req.TaskRequest;

public interface TaskCreateUseCase {
    void createTask(TaskRequest taskRequest);
}
