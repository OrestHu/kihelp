package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.config.MyTelegramBot;
import org.example.kihelp.task.model.req.HistoryRequest;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TaskProgramUseCaseImpl implements TaskProgramUseCase {
    @Value("${file.directory}")
    private String FILE_DIRECTORY;

    private final TaskService taskService;
    private final TaskGetUseCase taskGetUseCase;
    private final MyTelegramBot myTelegramBot;
    private final WalletValidateUseCase walletValidateUseCase;
    private final UserApiService userApiService;
    private final HistoryCreateUseCase historyCreateUseCase;
    private final WalletUpdateUseCase walletUpdateUseCase;

    @Override
    public TaskProgramResponse programTask(Integer taskId, TaskProgramRequest request) throws IOException {
        var user = userApiService.currentUserAccount();
        var task = taskGetUseCase.getTaskInfo(taskId);
        var price = task.type() ? task.price() : task.price() * request.args().size();

        walletValidateUseCase.validatedBalance(user.userId(), price);

        var fileName = taskService.programTask(taskId, request, user);
        var filePath = FILE_DIRECTORY + fileName;
        var path = Paths.get(filePath);

        try {
            myTelegramBot.sendDocument(Long.parseLong(user.telegramId()), filePath, task);
        } finally {
            Files.deleteIfExists(path);
        }

        deductPaymentFromWallet(user.userId(), price);
        saveHistory(fileName, user.userId(), taskId);

        return new TaskProgramResponse(fileName, Instant.now().toString());
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