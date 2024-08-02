package org.example.kihelp.task.mapper.impl;

import org.example.kihelp.task.mapper.HistoryToHistoryResponseMapper;
import org.example.kihelp.task.model.History;
import org.example.kihelp.task.model.resp.HistoryResponse;
import org.springframework.stereotype.Component;

@Component
public class HistoryToHistoryResponseMapperImpl implements HistoryToHistoryResponseMapper {

    @Override
    public HistoryResponse map(History history) {
        return new HistoryResponse(
                history.getId(),
                history.getName(),
                history.getCreatedTimeStamp().toString(),
                history.getUser().getId(),
                history.getTask().getId()
        );
    }
}
