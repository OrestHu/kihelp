package org.example.kihelp.task.config;

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
        return "helper_web_best_hackothon_bot";
    }

    @Override
    public String getBotToken() {
        return "6721007474:AAH3Dn8kwjx6JmEZHadMo7xYoRR9tmOTfgI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Тут ви можете обробляти вхідні повідомлення
    }

    public String sendDocument(Long chatId, String filePath) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId.toString());
        sendDocument.setDocument(new InputFile(new File(filePath)));

        try {
            Message message = execute(sendDocument);
            if (message != null && message.getDocument() != null) {
                return "https://t.me/" + getBotUsername() + "?document=" + message.getDocument().getFileId();
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return "";
    }
}
