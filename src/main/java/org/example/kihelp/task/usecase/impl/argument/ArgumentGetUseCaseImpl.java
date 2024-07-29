package org.example.kihelp.task.usecase.impl.argument;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.ArgumentToArgumentResponseMapper;
import org.example.kihelp.task.model.resp.ArgumentResponse;
import org.example.kihelp.task.service.ArgumentService;
import org.example.kihelp.task.usecase.argument.ArgumentGetUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArgumentGetUseCaseImpl implements ArgumentGetUseCase {
    private final ArgumentService argumentService;
    private final ArgumentToArgumentResponseMapper argumentToArgumentResponseMapper;

    @Override
    public List<ArgumentResponse> getArguments() {
        var arguments = argumentService.getArguments();

        return arguments.stream().map(argumentToArgumentResponseMapper::map).toList();
    }

    @Override
    public ArgumentResponse getArgument(Integer argumentId) {
        var argument = argumentService.getArgument(argumentId);

        return argumentToArgumentResponseMapper.map(argument);
    }
}
