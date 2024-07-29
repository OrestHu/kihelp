package org.example.kihelp.task.usecase.task;

import jakarta.validation.Valid;
import org.example.kihelp.task.model.req.TaskUpdateRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TaskUpdateUseCase {
    void updateTask(Integer taskId, @Valid TaskUpdateRequest request);
}
