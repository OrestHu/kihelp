package org.example.kihelp.task.mapper.impl;

import org.example.kihelp.task.mapper.ArgumentRequestToArgumentMapper;
import org.example.kihelp.task.model.Argument;
import org.example.kihelp.task.model.req.ArgumentRequest;
import org.springframework.stereotype.Component;

@Component
public class ArgumentRequestToArgumentMapperImpl implements ArgumentRequestToArgumentMapper {

    @Override
    public Argument map(ArgumentRequest argumentRequest) {
        Argument argument = new Argument();

        argument.setName(argumentRequest.name());

        return argument;
    }
}
