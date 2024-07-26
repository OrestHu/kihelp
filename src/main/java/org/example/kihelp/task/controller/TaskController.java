package org.example.kihelp.task.controller;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.req.TaskRequest;
import org.example.kihelp.task.model.resp.TaskInfoResponse;
import org.example.kihelp.task.model.resp.TaskProgramResponse;
import org.example.kihelp.task.model.resp.TestInfoResponse;
import org.example.kihelp.task.usecase.TaskCreateUseCase;
import org.example.kihelp.task.usecase.TaskGetUseCase;
import org.example.kihelp.task.usecase.TaskProgramUseCase;
import org.example.kihelp.task.usecase.TestGetUseCase;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskCreateUseCase taskCreateUseCase;
    private final TaskGetUseCase taskGetUseCase;
    private final TaskProgramUseCase taskProgramUseCase;
    private final TestGetUseCase testGetUseCase;

    @PostMapping("/task")
    public void createTask(@RequestBody TaskRequest taskRequest) {
        taskCreateUseCase.createTask(taskRequest);
    }

    @GetMapping("/task/info/{task_id}")
    public TaskInfoResponse getTaskInfo(@PathVariable("task_id") Integer taskId) {
        return taskGetUseCase.getTaskInfo(taskId);
    }

    @GetMapping("/test/info/{test_id}/{repeat_count}")
    public TestInfoResponse getTestInfo(@PathVariable("test_id") Integer testId,
                                        @PathVariable("repeat_count") Integer repeatCount){
        return testGetUseCase.getTestInfo(testId, repeatCount);
    }

    @GetMapping(value = "/task/program/{task_id}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public TaskProgramResponse programTask(@PathVariable("task_id") Integer taskId,
                                           @RequestBody TaskProgramRequest programRequest) throws IOException {
        return taskProgramUseCase.programTask(taskId, programRequest);
    }
}
