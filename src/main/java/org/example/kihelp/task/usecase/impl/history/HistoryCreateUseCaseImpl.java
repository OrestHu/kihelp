package org.example.kihelp.task.usecase.impl.history;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.HistoryRequestToHistoryMapper;
import org.example.kihelp.task.model.req.HistoryRequest;
import org.example.kihelp.task.service.HistoryService;
import org.example.kihelp.task.usecase.history.HistoryCreateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryCreateUseCaseImpl implements HistoryCreateUseCase {
    private final HistoryService historyService;
    private final HistoryRequestToHistoryMapper historyRequestToHistoryMapper;

    @Override
    public void createHistory(HistoryRequest historyRequest) {
        var history = historyRequestToHistoryMapper.map(historyRequest);

        historyService.addHistory(history);
    }
}
