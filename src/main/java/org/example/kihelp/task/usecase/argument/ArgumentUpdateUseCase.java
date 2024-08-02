package org.example.kihelp.task.usecase.argument;

import org.example.kihelp.task.model.req.ArgumentRequest;

public interface ArgumentUpdateUseCase {
    void updateArgument(Integer argumentId, ArgumentRequest argumentRequest);
}
