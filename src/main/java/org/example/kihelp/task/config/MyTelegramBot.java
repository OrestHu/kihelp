package org.example.kihelp.task.config;

import org.example.kihelp.task.exception.MassageNotSentToTelegramException;
import org.example.kihelp.task.model.resp.TaskResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import static org.example.kihelp.task.util.MessageError.MESSAGE_BLOCKED_MY_USER;

@Configuration
public class MyTelegramBot extends TelegramLongPollingBot {
    @Value("${telegram.token}")
    private String token;

    @Value("${telegram.username}")
    private String username;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendDocument(Long chatId, String filePath, TaskResponse response) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId.toString());
        sendDocument.setDocument(new InputFile(new File(filePath)));
        sendDocument.setCaption(String.format("*Відповідь до %s*\nз предмету %s", response.title(), response.subjectTitle()));
        sendDocument.setParseMode("Markdown");

        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            if (e.getMessage().contains("Forbidden: bot was blocked by the user")) {
                throw new MassageNotSentToTelegramException(MESSAGE_BLOCKED_MY_USER);
            } else {
                throw new MassageNotSentToTelegramException(e.getMessage());
            }
        }
    }
}
