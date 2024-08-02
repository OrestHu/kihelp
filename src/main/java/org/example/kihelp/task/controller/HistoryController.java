package org.example.kihelp.task.controller;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.resp.HistoryAmountResponse;
import org.example.kihelp.task.model.resp.HistoryResponse;
import org.example.kihelp.task.usecase.history.HistoryGetUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/histories")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryGetUseCase historyGetUseCase;

    @GetMapping("/history")
    public List<HistoryResponse> getHistories(){
        return historyGetUseCase.getHistories();
    }

    @GetMapping("/history/user/{user_id}")
    public List<HistoryResponse> getHistories(@PathVariable("user_id") Long userId){
        return historyGetUseCase.getHistoriesByUser(userId);
    }

    @GetMapping("/history/amount/{user_id}")
    public HistoryAmountResponse getAmountOfMoneyByUser(@PathVariable("user_id") Long userId){
        return historyGetUseCase.getAmountOfMoneyByUser(userId);
    }
}
