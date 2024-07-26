package org.example.kihelp.task.usecase;

import org.example.kihelp.task.model.resp.TestInfoResponse;

public interface TestGetUseCase {
    TestInfoResponse getTestInfo(Integer testId, Integer repeatCount);
}
