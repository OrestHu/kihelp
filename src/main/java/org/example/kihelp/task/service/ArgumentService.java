package org.example.kihelp.task.service;

import org.example.kihelp.task.model.Argument;
import org.example.kihelp.task.model.req.ArgumentRequest;

import java.util.List;

public interface ArgumentService {
    void createArgument(Argument argument);
    Argument getArgument(Integer argumentId);
    List<Argument> getArguments();
    void deleteArgument(Integer argumentId);
    void updateArgument(Integer argumentId, ArgumentRequest request);
}
