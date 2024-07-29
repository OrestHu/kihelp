package org.example.kihelp.task.usecase.impl.argument;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.ArgumentRequestToArgumentMapper;
import org.example.kihelp.task.model.req.ArgumentRequest;
import org.example.kihelp.task.service.ArgumentService;
import org.example.kihelp.task.usecase.argument.ArgumentCreateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArgumentCreateUseCaseImpl implements ArgumentCreateUseCase {
    private final ArgumentService argumentService;
    private final ArgumentRequestToArgumentMapper argumentRequestToArgumentMapper;

    @Override
    public void createArgument(ArgumentRequest argumentRequest) {
        var argument = argumentRequestToArgumentMapper.map(argumentRequest);

        argumentService.createArgument(argument);
    }
}
