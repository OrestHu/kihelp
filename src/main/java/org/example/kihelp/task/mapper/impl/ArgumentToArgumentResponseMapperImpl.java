package org.example.kihelp.task.mapper.impl;

import org.example.kihelp.task.mapper.ArgumentToArgumentResponseMapper;
import org.example.kihelp.task.model.Argument;
import org.example.kihelp.task.model.resp.ArgumentResponse;
import org.springframework.stereotype.Component;

@Component
public class ArgumentToArgumentResponseMapperImpl implements ArgumentToArgumentResponseMapper {

    @Override
    public ArgumentResponse map(Argument argument) {
        return new ArgumentResponse(
            argument.getId(),
            argument.getName()
        );
    }
}
