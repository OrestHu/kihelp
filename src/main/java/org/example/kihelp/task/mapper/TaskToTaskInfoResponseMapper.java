package org.example.kihelp.task.mapper;

import org.example.kihelp.subject.mapper.Mapper;
import org.example.kihelp.task.model.Task;
import org.example.kihelp.task.model.resp.TaskInfoResponse;

public interface TaskToTaskInfoResponseMapper extends Mapper<TaskInfoResponse, Task> {
}
