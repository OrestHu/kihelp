package org.example.kihelp.task.usecase.impl.task;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.task.config.MyTelegramBot;
import org.example.kihelp.task.exception.NotEnoughAmountException;
import org.example.kihelp.task.model.req.TaskProgramRequest;
import org.example.kihelp.task.service.TaskService;
import org.example.kihelp.task.usecase.task.TaskGetUseCase;
import org.example.kihelp.task.usecase.task.TaskProgramUseCase;
import org.example.kihelp.user.api.service.UserApiService;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.service.WalletService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.example.kihelp.task.util.MessageError.NOT_ENOUGH_MONEY;

@Component
@RequiredArgsConstructor
public class TaskProgramUseCaseImpl implements TaskProgramUseCase {
    private final String FILE_DIRECTORY = "tasks/answer/";
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

        if(wallet.getAmount() < taskInfo.price()){
            throw new NotEnoughAmountException(
                    String.format(NOT_ENOUGH_MONEY, taskInfo.price() - wallet.getAmount())
            );
        }

        var nameOfFile = taskService.programTask(taskId, request);
        var path = Paths.get(FILE_DIRECTORY + nameOfFile);

        myTelegramBot.sendDocument(
                Long.parseLong(user.telegramId()),
                FILE_DIRECTORY + nameOfFile,
                taskInfo
        );

        Files.delete(path);

        walletService.topUpWallet(wallet.getId(), new WalletRequest((double) -taskInfo.price()));
    }
}