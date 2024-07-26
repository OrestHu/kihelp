package org.example.kihelp.task.usecase;

import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.resp.TaskProgramResponse;

import java.io.IOException;

public interface TaskProgramUseCase {
    TaskProgramResponse programTask(Integer taskId, TaskProgramRequest request) throws IOException;
}
