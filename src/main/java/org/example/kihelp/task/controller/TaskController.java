package org.example.kihelp.task.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.req.TaskRequest;
import org.example.kihelp.task.model.req.TaskUpdateRequest;
import org.example.kihelp.task.model.resp.TaskProgramResponse;
import org.example.kihelp.task.model.resp.TaskResponse;
import org.example.kihelp.task.usecase.task.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskCreateUseCase taskCreateUseCase;
    private final TaskGetUseCase taskGetUseCase;
    private final TestGetUseCase testGetUseCase;
    private final TaskProgramUseCase taskProgramUseCase;
    private final TaskDeleteUseCase taskDeleteUseCase;
    private final TaskUpdateUseCase taskUpdateUseCase;

    @PostMapping("/task")
    public void createTask(@Valid @RequestBody TaskRequest taskRequest) {
        taskCreateUseCase.createTask(taskRequest);
    }

    @GetMapping("/task/teacher/{teacher_id}")
    public List<TaskResponse> getTasksByTeacher(@PathVariable("teacher_id") Integer teacherId) {
        return taskGetUseCase.getTasksByTeacher(teacherId);
    }

    @GetMapping("/task/{task_id}")
    public TaskResponse getTaskInfo(@PathVariable("task_id") Integer taskId) {
        return taskGetUseCase.getTaskInfo(taskId);
    }

    @GetMapping("/test/{test_id}/{repeat_count}")
    public TaskResponse getTestInfo(@PathVariable("test_id") Integer testId,
                                    @PathVariable("repeat_count") Integer repeatCount){
        return testGetUseCase.getTestInfo(testId, repeatCount);
    }

    @GetMapping("/task/program/{task_id}")
    public TaskProgramResponse programTask(@PathVariable("task_id") Integer taskId,
                                           @RequestBody TaskProgramRequest programRequest) throws IOException {
        return taskProgramUseCase.programTask(taskId, programRequest);
    }

    @DeleteMapping("/task/{task_id}")
    public void deleteTask(@PathVariable("task_id") Integer taskId) {
        taskDeleteUseCase.deleteTask(taskId);
    }

    @PatchMapping("/task/{task_id}")
    public void updateTask(@PathVariable("task_id") Integer taskId,
                           @Valid @RequestBody TaskUpdateRequest taskRequest){
        taskUpdateUseCase.updateTask(taskId, taskRequest);
    }
}
