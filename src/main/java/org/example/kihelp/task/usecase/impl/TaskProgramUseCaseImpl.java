package org.example.kihelp.task.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.resp.TaskProgramResponse;
import org.example.kihelp.task.service.AttachmentService;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.TaskProgramUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class TaskProgramUseCaseImpl implements TaskProgramUseCase {

    @Value("${answer}")
    private String FILE_DIRECTORY;

    private final TaskService taskService;
    private final AttachmentService attachmentService;

    @Override
    public TaskProgramResponse programTask(Integer taskId, TaskProgramRequest request) throws IOException {
        String nameOfFile = taskService.programTask(taskId, request);

        var path = Paths.get(FILE_DIRECTORY+nameOfFile);
        var file = Files.readAllBytes(path);

        System.out.println(file.toString());

        return null;
    }
}