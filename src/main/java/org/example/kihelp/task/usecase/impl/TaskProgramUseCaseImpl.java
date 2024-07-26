package org.example.kihelp.task.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.resp.TaskProgramResponse;
import org.example.kihelp.task.service.AttachmentService;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.TaskProgramUseCase;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
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
//        var nameOfFile = taskService.programTask(taskId, request);
//        var name = FILE_DIRECTORY + nameOfFile;
//
//        var content = Files.readAllBytes(Paths.get(name));
////        var file = new MockMultipartFile(
////                nameOfFile.split("\\.")[0],
////                nameOfFile,
////                "text/plain",
////                new ByteArrayInputStream(content)
////        );
////
////        var attachment = attachmentService.storeFile(file);
////        Files.delete(Paths.get(name));
//
//        return new TaskProgramResponse(attachment.getName(), attachment.getType(), attachment.getData());
        return null;
    }
}
