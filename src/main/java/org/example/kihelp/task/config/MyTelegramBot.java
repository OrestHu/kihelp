package org.example.kihelp.task.config;

import org.example.kihelp.task.exception.MassageNotSentToTelegramException;
import org.example.kihelp.task.model.resp.TaskResponse;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

@Configuration
public class MyTelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "ToiletTracker_bot";
    }

    @Override
    public String getBotToken() {
        return "7045776273:AAFUmjspdhEvU-pRVTO_xmeM4anPyqd1MwY";
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendDocument(Long chatId, String filePath, TaskResponse response) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId.toString());
        sendDocument.setDocument(new InputFile(new File(filePath)));
        sendDocument.setCaption(String.format("*Відповідь до %s*\nз предмету %s", response.title(), response.subjectTitle()));  // Використовуємо \n для нового рядка
        sendDocument.setParseMode("Markdown");

        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            throw new MassageNotSentToTelegramException(e.getMessage());
        }
    }
}
