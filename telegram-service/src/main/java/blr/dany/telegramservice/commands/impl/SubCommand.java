package blr.dany.telegramservice.commands.impl;

import blr.dany.telegramservice.commands.CommandHandler;
import blr.dany.telegramservice.feign.UserServiceClient;
import blr.dany.telegramservice.feign.response.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SubCommand implements CommandHandler {

    private final UserServiceClient userServiceClient;

    @Override
    public SendMessage handle(long chatId, Update update) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        ResponseEntity<TelegramUser> response = userServiceClient.getUserByChatId(String.valueOf(chatId));
        TelegramUser user = response.getBody();
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        if (user.getIsSub()){
            sendMessage.setText("Желаете отписаться от рассылки экстренных уведомлений ?");
            List<InlineKeyboardButton> row = Arrays.asList(
                    createButton("Отписаться", "sub:no"),
                    createButton("Назад", "sub:skip")
            );
            rows.add(row);
            markup.setKeyboard(rows);
            sendMessage.setReplyMarkup(markup);
            return sendMessage;
        } else {
            sendMessage.setText("Желаете подписаться на рассылку экстренных уведомлений ?");
            List<InlineKeyboardButton> row = Arrays.asList(
                    createButton("Подписаться", "sub:yes"),
                    createButton("Назад", "sub:skip")
            );
            rows.add(row);
            markup.setKeyboard(rows);
            sendMessage.setReplyMarkup(markup);
            return sendMessage;
        }
    }

    private InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
