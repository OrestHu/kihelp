package org.example.kihelp.task.usecase.impl.argument;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.service.ArgumentService;
import org.example.kihelp.task.usecase.argument.ArgumentDeleteUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArgumentDeleteUseCaseImpl implements ArgumentDeleteUseCase {
    private final ArgumentService argumentService;

    @Override
    public void deleteArgument(Integer argumentId) {
        argumentService.deleteArgument(argumentId);
    }
}
