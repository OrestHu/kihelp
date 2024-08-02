package org.example.kihelp.task.usecase.impl.history;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.mapper.HistoryToHistoryResponseMapper;
import org.example.kihelp.task.model.resp.HistoryAmountResponse;
import org.example.kihelp.task.model.resp.HistoryResponse;
import org.example.kihelp.task.service.HistoryService;
import org.example.kihelp.task.usecase.history.HistoryGetUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoryGetUseCaseImpl implements HistoryGetUseCase {
    private final HistoryService historyService;
    private final HistoryToHistoryResponseMapper historyToHistoryResponseMapper;

    @Override
    public List<HistoryResponse> getHistories() {
        var histories = historyService.getHistories();

        return histories.stream().map(historyToHistoryResponseMapper::map).toList();
    }

    @Override
    public List<HistoryResponse> getHistoriesByUser(Long userId) {
        var histories = historyService.getHistoriesByUser(userId);


        return histories.stream().map(historyToHistoryResponseMapper::map).toList();
    }

    @Override
    public HistoryAmountResponse getAmountOfMoneyByUser(Long userId) {
        var amount = historyService.getAmountOfMoneyByUser(userId);

        return new HistoryAmountResponse(amount);
    }
}
