package org.example.kihelp.task.service;

import org.example.kihelp.task.model.History;

import java.util.List;

public interface HistoryService {
    void addHistory(History history);
    List<History> getHistories();
    List<History> getHistoriesByUser(Long userId);
    Double getAmountOfMoneyByUser(Long userId);
}
