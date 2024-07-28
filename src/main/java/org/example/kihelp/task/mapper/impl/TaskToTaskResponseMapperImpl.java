package org.example.kihelp.task.mapper.impl;

import org.example.kihelp.task.mapper.TaskToTaskResponseMapper;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.resp.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskResponseMapperImpl implements TaskToTaskResponseMapper {

    @Override
    public TaskResponse map(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getPrice(),
                task.getDiscount(),
                task.getSubject().getId(),
                task.getTeacher().getId()
        );
    }
}
