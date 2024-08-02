package org.example.kihelp.task.usecase.history;

import org.example.kihelp.task.model.req.HistoryRequest;

public interface HistoryCreateUseCase {
    void createHistory(HistoryRequest historyRequest);
}
