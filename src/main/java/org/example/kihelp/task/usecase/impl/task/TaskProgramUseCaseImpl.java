package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.config.MyTelegramBot;
import org.example.kihelp.task.model.Attachment;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.resp.TaskProgramResponse;
import org.example.kihelp.task.service.AttachmentService;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.task.TaskProgramUseCase;
import org.example.kihelp.user.api.service.UserApiService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class TaskProgramUseCaseImpl implements TaskProgramUseCase {
    private final String FILE_DIRECTORY = "tasks/answer/";
    private final TaskService taskService;
    private final AttachmentService attachmentService;
    private final MyTelegramBot myTelegramBot;
    private final UserApiService userApiService;

    @Override
    public TaskProgramResponse programTask(Integer taskId, TaskProgramRequest request) throws IOException {
        var user = userApiService.currentUserAccount();
        var nameOfFile = taskService.programTask(taskId, request);
        var path = Paths.get(FILE_DIRECTORY + nameOfFile);
        var fileType = Files.probeContentType(path);
        var link = myTelegramBot.sendDocument(Long.parseLong(user.telegramId()), FILE_DIRECTORY + nameOfFile);

        var attachment = new Attachment().builder()
                .name(nameOfFile)
                .type(fileType)
                .link(link)
                .build();

        attachmentService.storeFile(attachment);

        Files.delete(path);

        return new TaskProgramResponse(attachment.getName(), attachment.getType(), link);
    }
}