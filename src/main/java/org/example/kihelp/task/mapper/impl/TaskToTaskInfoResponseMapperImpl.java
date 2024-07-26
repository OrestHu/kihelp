package org.example.kihelp.task.mapper.impl;

import org.example.kihelp.task.mapper.TaskToTaskInfoResponseMapper;
import org.example.kihelp.task.model.Argument;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.resp.TaskInfoResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskInfoResponseMapperImpl implements TaskToTaskInfoResponseMapper {

    @Override
    public TaskInfoResponse map(Task task) {
        var args = task.getArguments().stream().map(Argument::getName).toList();

        return new TaskInfoResponse(
                task.getTitle(),
                args
        );
    }
}
