package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.config.MyTelegramBot;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.task.TaskGetUseCase;
import org.example.kihelp.task.usecase.task.TaskProgramUseCase;
import org.example.kihelp.user.api.service.UserApiService;
import org.example.kihelp.wallet.model.Wallet;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class TaskProgramUseCaseImpl implements TaskProgramUseCase {
    @Value("${file.directory}")
    private String FILE_DIRECTORY;

    private final TaskService taskService;
    private final MyTelegramBot myTelegramBot;
    private final TaskGetUseCase taskGetUseCase;
    private final WalletService walletService;
    private final UserApiService userApiService;

    @Override
    public void programTask(Integer taskId, TaskProgramRequest request) throws IOException {
        var user = userApiService.currentUserAccount();
        var taskInfo = taskGetUseCase.getTaskInfo(taskId);
        var wallet = walletService.getWalletByUser(user.userId());

        var price = taskInfo.type() ? taskInfo.price() : taskInfo.price() * request.args().size();

        walletService.validatedBalance(wallet, price);

        var nameOfFile = taskService.programTask(taskId, request);
        var path = Paths.get(FILE_DIRECTORY + nameOfFile);

        try {
            myTelegramBot.sendDocument(
                    Long.parseLong(user.telegramId()),
                    FILE_DIRECTORY + nameOfFile,
                    taskInfo
            );
        } finally {
            Files.delete(path);
        }

        deductPaymentFromWallet(wallet, price);
    }

    private void deductPaymentFromWallet(Wallet wallet, Double amount) {
        walletService.topUpWallet(wallet.getId(), new WalletRequest(-amount));
    }
}