package org.example.kihelp.task.usecase;

import jakarta.validation.Valid;
import org.example.kihelp.task.model.req.TaskRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TaskCreateUseCase {
    void createTask(@Valid TaskRequest taskRequest);
}
