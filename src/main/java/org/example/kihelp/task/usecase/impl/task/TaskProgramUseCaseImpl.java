package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.model.req.HistoryRequest;
import org.example.kihelp.task.model.resp.ProgramResponse;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.model.resp.TaskProgramResponse;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.history.HistoryCreateUseCase;
import org.example.kihelp.task.usecase.task.TaskGetUseCase;
import org.example.kihelp.task.usecase.task.TaskProgramUseCase;
import org.example.kihelp.user.api.service.UserApiService;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.usecase.WalletUpdateUseCase;
import org.example.kihelp.wallet.usecase.WalletValidateUseCase;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TaskProgramUseCaseImpl implements TaskProgramUseCase {
    private final TaskService taskService;
    private final TaskGetUseCase taskGetUseCase;
    private final WalletValidateUseCase walletValidateUseCase;
    private final UserApiService userApiService;
    private final HistoryCreateUseCase historyCreateUseCase;
    private final WalletUpdateUseCase walletUpdateUseCase;

    @Override
    public TaskProgramResponse programTask(Integer taskId, TaskProgramRequest request){
        var user = userApiService.currentUserAccount();
        var taskResp = taskGetUseCase.getTaskInfo(taskId);
        var price = taskResp.type() ? taskResp.price() : taskResp.price() * request.args().size();

        walletValidateUseCase.validatedBalance(user.userId(), price);

        var programResponse = taskService.programTask(taskId, request, user);

        deductPaymentFromWallet(user.userId(), price);
        saveHistory(programResponse.fileName(), user.userId(), taskId);

        return new TaskProgramResponse(programResponse.fileName(), Instant.now().toString());
    }

    private void deductPaymentFromWallet(Long userId, Double amount) {
        walletUpdateUseCase.topUpWallet(userId, new WalletRequest(-amount));
    }

    private void saveHistory(String nameOfFile, Long userId, Integer taskId) {
        historyCreateUseCase.createHistory(
                new HistoryRequest(
                        nameOfFile,
                        Instant.now(),
                        userId,
                        taskId
                ));
    }
}