package org.example.kihelp.task.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.HistoryRequestToHistoryMapper;
import org.example.kihelp.task.model.History;
import org.example.kihelp.task.model.req.HistoryRequest;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryRequestToHistoryMapperImpl implements HistoryRequestToHistoryMapper {
    private final UserService userService;
    private final TaskService taskService;

    @Override
    public History map(HistoryRequest historyRequest) {
        History history = new History();

        history.setName(historyRequest.name());
        history.setCreatedTimeStamp(historyRequest.createdTimeStamp());

        var user = userService.getUser(historyRequest.userId());
        var task = taskService.getTaskById(historyRequest.taskId());

        history.setUser(user);
        history.setTask(task);

        return history;
    }
}
