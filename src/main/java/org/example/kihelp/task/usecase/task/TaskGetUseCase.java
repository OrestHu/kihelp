package org.example.kihelp.task.usecase.task;

import org.example.kihelp.task.model.resp.TaskResponse;

import java.util.List;

public interface TaskGetUseCase {
    TaskResponse getTaskInfo(Integer taskId);
    List<TaskResponse> getTasksByTeacher(Integer teacherId);
}
