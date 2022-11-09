package com.alexander;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private final InlineKeyboardButton button = new InlineKeyboardButton();
    private final List<InlineKeyboardButton> buttonRow = new ArrayList<>();
    private final List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    private final SendMessage sendMessage = new SendMessage();
    SendMediaGroup mediaGroup = new SendMediaGroup();
    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
       // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
                // Set variables
                String message_text = update.getMessage().getText();
                Long chat_id = update.getMessage().getChatId();

                sendMessage.setChatId(chat_id);
                if (message_text.equals("/start")) {
                    createButton("/start");
                } else if (message_text.equals("/stoc_disponibil")){
                    createButton("/stoc_disponibil");
                }else{
                    sendMessage.setText("Tastati comanda /start");
                }
                try {
                    execute(sendMessage); // Sending our message object to user
                 } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

        } else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();

            if(data.equals("Stoc")){
                sendMessage.setChatId(message.getChatId());
                sendMessage.setText("Stocul de azi disponibil : ");
                String image1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Simba_His_majesty_dominating_the_Serengeti_plains%21_Serengeti_NP%2C_India_%2848788336273%29.jpg/1200px-Simba_His_majesty_dominating_the_Serengeti_plains%21_Serengeti_NP%2C_India_%2848788336273%29.jpg";
                String image2 = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Simba_His_majesty_dominating_the_Serengeti_plains%21_Serengeti_NP%2C_India_%2848788336273%29.jpg/1200px-Simba_His_majesty_dominating_the_Serengeti_plains%21_Serengeti_NP%2C_India_%2848788336273%29.jpg";
                String image3 = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Simba_His_majesty_dominating_the_Serengeti_plains%21_Serengeti_NP%2C_India_%2848788336273%29.jpg/1200px-Simba_His_majesty_dominating_the_Serengeti_plains%21_Serengeti_NP%2C_India_%2848788336273%29.jpg";

                List<InputMedia> media = new ArrayList<>();
                InputMedia photo1 = new InputMediaPhoto();
                photo1.setMedia(image1);
                InputMedia photo2 = new InputMediaPhoto();
                photo2.setMedia(image2);
                InputMedia photo3 = new InputMediaPhoto();
                photo3.setMedia(image3);
                media.add(photo1);
                media.add(photo2);
                media.add(photo3);
                mediaGroup.setChatId(message.getChatId());
                mediaGroup.setMedias(media);
            }
            try {
                execute(sendMessage);
                execute(mediaGroup);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
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

    private void createButton(String command){
            if(command.equals("/start")) {
                clearCachedLists();

                sendMessage.setText("Bine ati venit la magazinul nostru");

                button.setText("Stoc Disponibil!");
                button.setCallbackData("Stoc");

                buttonRow.add(button);
                rowList.add(buttonRow);

                inlineKeyboardMarkup.setKeyboard(rowList);

                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            } else if (command.equals("/stoc_disponibil")) {
                clearCachedLists();

                sendMessage.setText("Apasa pe butonul de mai jos!");
                button.setText("Stoc Disponibil!");
                button.setCallbackData("Stoc");

                buttonRow.add(button);
                rowList.add(buttonRow);

                inlineKeyboardMarkup.setKeyboard(rowList);

                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            }

    }
    private void clearCachedLists(){
        buttonRow.clear();
        rowList.clear();
    }
}
