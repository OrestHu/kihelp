package org.example.kihelp.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.Argument;
import org.example.kihelp.task.repository.ArgumentRepository;
import org.example.kihelp.task.service.ArgumentService;
import org.springframework.stereotype.Service;

import static org.example.kihelp.task.util.MessageError.ARGUMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ArgumentServiceImpl implements ArgumentService {
    private final ArgumentRepository argumentRepository;

    @Override
    public Argument getArgument(Integer argumentId) {
        return argumentRepository.findById(argumentId)
                .orElseThrow(
                        () -> new RuntimeException(ARGUMENT_NOT_FOUND)
                );
    }
}
