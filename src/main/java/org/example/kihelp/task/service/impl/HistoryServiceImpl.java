package org.example.kihelp.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.History;
import org.example.kihelp.task.repository.HistoryRepository;
import org.example.kihelp.task.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    @Override
    public void addHistory(History history) {
        historyRepository.save(history);
    }

    @Override
    public List<History> getHistories() {
        return historyRepository.findAll();
    }

    @Override
    public List<History> getHistoriesByUser(Long userId) {
        return historyRepository.findAllByUserId(userId);
    }

    @Override
    public Double getAmountOfMoneyByUser(Long userId) {
        var histories = getHistoriesByUser(userId);

        return histories.stream()
                .map(h -> h.getTask().getPrice())
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public void deleteHistoriesByTask(Integer taskId) {
        var histories = historyRepository.findAllByTaskId(taskId);

        historyRepository.deleteAll(histories);
    }
}
