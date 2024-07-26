package org.example.kihelp.task.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.TestToTestInfoResponseMapper;
import org.example.kihelp.task.model.resp.TestInfoResponse;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.TestGetUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestGetUseCaseImpl implements TestGetUseCase {
    private final TaskService taskService;
    private final TestToTestInfoResponseMapper testToTestInfoResponseMapper;

    @Override
    public TestInfoResponse getTestInfo(Integer testId, Integer repeatCount) {
        var task = taskService.getTaskById(testId);

        return testToTestInfoResponseMapper.map(task, repeatCount);
    }
}
