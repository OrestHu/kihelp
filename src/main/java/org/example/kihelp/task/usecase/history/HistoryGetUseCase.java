package org.example.kihelp.task.usecase.history;

import org.example.kihelp.task.model.resp.HistoryAmountResponse;
import org.example.kihelp.task.model.resp.HistoryResponse;

import java.util.List;

public interface HistoryGetUseCase {
    List<HistoryResponse> getHistories();
    List<HistoryResponse> getHistoriesByUser(Long userId);
    HistoryAmountResponse getAmountOfMoneyByUser(Long userId);
}
