package org.example.kihelp.task.usecase.impl.argument;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.ArgumentRequest;
import org.example.kihelp.task.service.ArgumentService;
import org.example.kihelp.task.usecase.argument.ArgumentUpdateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArgumentUpdateUseCaseImpl implements ArgumentUpdateUseCase {
    private final ArgumentService argumentService;

    @Override
    public void updateArgument(Integer argumentId, ArgumentRequest argumentRequest) {
        argumentService.updateArgument(argumentId, argumentRequest);
    }
}
