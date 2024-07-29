package org.example.kihelp.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.exception.ArgumentAlreadyExistException;
import org.example.kihelp.task.exception.ArgumentNotFoundException;
import org.example.kihelp.task.model.Argument;
import org.example.kihelp.task.repository.ArgumentRepository;
import org.example.kihelp.task.service.ArgumentService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.kihelp.task.util.MessageError.ARGUMENT_ALREADY_EXIST;
import static org.example.kihelp.task.util.MessageError.ARGUMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ArgumentServiceImpl implements ArgumentService {
    private final ArgumentRepository argumentRepository;

    @Override
    public void createArgument(Argument argument) {
        var exist = argumentRepository.existsByName(argument.getName());

        if (exist){
            throw new ArgumentAlreadyExistException(String.format(ARGUMENT_ALREADY_EXIST, argument.getName()));
        }

        argumentRepository.save(argument);
    }

    @Override
    public Argument getArgument(Integer argumentId) {
        return argumentRepository.findById(argumentId)
                .orElseThrow(
                        () -> new ArgumentNotFoundException(ARGUMENT_NOT_FOUND)
                );
    }

    @Override
    public List<Argument> getArguments() {
        return argumentRepository.findAll();
    }
}
