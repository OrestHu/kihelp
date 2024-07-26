package org.example.kihelp.task.service;

import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.req.TaskProgramRequest;

public interface TaskService {
    void createTask(Task task);
    Task getTaskById(Integer taskId);
    String programTask(Integer taskId, TaskProgramRequest request);
}
