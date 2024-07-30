package org.example.kihelp.task.usecase.task;

import org.example.kihelp.task.model.req.TaskProgramRequest;

import java.io.IOException;

public interface TaskProgramUseCase {
    void programTask(Integer taskId, TaskProgramRequest request) throws IOException;
}
