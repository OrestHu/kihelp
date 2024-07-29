package org.example.kihelp.task.usecase.argument;

import org.example.kihelp.task.model.resp.ArgumentResponse;

import java.util.List;

public interface ArgumentGetUseCase {
    List<ArgumentResponse> getArguments();
    ArgumentResponse getArgument(Integer argumentId);
}
