package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.Attachment;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.resp.TaskProgramResponse;
import org.example.kihelp.task.service.AttachmentService;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.task.TaskProgramUseCase;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class TaskProgramUseCaseImpl implements TaskProgramUseCase {
    private final String FILE_DIRECTORY = "tasks/answer/";
    private final TaskService taskService;
    private final AttachmentService attachmentService;

    @Override
    public TaskProgramResponse programTask(Integer taskId, TaskProgramRequest request) throws IOException {
        var nameOfFile = taskService.programTask(taskId, request);
        var path = Paths.get(FILE_DIRECTORY + nameOfFile);
        var fileBytes = Files.readAllBytes(path);
        var fileType = Files.probeContentType(path);

        var attachment = new Attachment().builder()
                .name(nameOfFile)
                .type(fileType)
                .data(fileBytes)
                .build();

        attachmentService.storeFile(attachment);

        Files.delete(path);

        return new TaskProgramResponse(attachment.getName(), attachment.getType(), attachment.getData());
    }
}