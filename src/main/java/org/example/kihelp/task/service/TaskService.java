package org.example.kihelp.task.service;

import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.req.TaskUpdateRequest;
import org.example.kihelp.teacher.model.Teacher;

import java.util.List;

public interface TaskService {
    void createTask(Task task);
    Task getTaskById(Integer taskId);
    List<Task> getTasksByTeacher(Teacher teacher);
    String programTask(Integer taskId, TaskProgramRequest request);
    void deleteTask(Integer taskId);
    void updateTask(Integer taskId, TaskUpdateRequest request);
}
