package org.example.kihelp.task.usecase;

import org.example.kihelp.task.model.resp.TaskInfoResponse;

public interface TaskGetUseCase {
    TaskInfoResponse getTaskInfo(Integer taskId);
}
