package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.TestToTestInfoResponseMapper;
import org.example.kihelp.task.model.resp.TaskResponse;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.task.TestGetUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestGetUseCaseImpl implements TestGetUseCase {
    private final TaskService taskService;
    private final TestToTestInfoResponseMapper testToTestInfoResponseMapper;

    @Override
    public TaskResponse getTestInfo(Integer testId, Integer repeatCount) {
        var task = taskService.getTaskById(testId);

        return testToTestInfoResponseMapper.map(task, repeatCount);
    }
}
