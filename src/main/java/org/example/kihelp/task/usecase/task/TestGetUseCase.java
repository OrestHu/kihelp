package org.example.kihelp.task.usecase.task;

import org.example.kihelp.task.model.resp.TaskResponse;

public interface TestGetUseCase {
    TaskResponse getTestInfo(Integer testId, Integer repeatCount);
}
