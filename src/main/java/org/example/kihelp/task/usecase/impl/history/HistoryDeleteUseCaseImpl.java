package org.example.kihelp.task.usecase.impl.history;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.service.HistoryService;
import org.example.kihelp.task.usecase.history.HistoryDeleteUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryDeleteUseCaseImpl implements HistoryDeleteUseCase {
    private final HistoryService historyService;

    @Override
    public void deleteHistoriesByTask(Integer taskId) {
        historyService.deleteHistoriesByTask(taskId);
    }
}
