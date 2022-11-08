package com.alexander;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            Long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage(); // Create a message object object
            message.setChatId(chat_id);
            message.setText(message_text);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("Suka!");
            button.setCallbackData("Suka has been pressed!");

            List<InlineKeyboardButton> buttonRow = new ArrayList<>();
            buttonRow.add(button);

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(buttonRow);

            inlineKeyboardMarkup.setKeyboard(rowList);

            message.setReplyMarkup(inlineKeyboardMarkup);

            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String getBotUsername() {
        return "alexandru_botnari_bot";
    }
    @Override
    public String getBotToken() {
        return "5717156868:AAF9VLB_xvaZ3tiDcaOArsQRCzcW_ybfk5c";
    }
}
