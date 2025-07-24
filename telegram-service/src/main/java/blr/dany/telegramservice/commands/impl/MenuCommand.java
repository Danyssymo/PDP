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
import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuCommand implements CommandHandler {

    private final UserServiceClient userServiceClient;

    @Override
    public SendMessage handle(long chatId, Update update) {

        ResponseEntity<TelegramUser> response = userServiceClient.getUserByChatId(String.valueOf(chatId));
        TelegramUser user = response.getBody();

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        String isSubText = user.getIsSub()
                ? "Вы подписаны "
                : "Вы не подписаны ";
        String urCountry = user.getCountry();

        message.setText("Меню:" + "\n" + "Ваша страна: " + urCountry + "\n" + "Подписка: " + isSubText);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        rows.add(List.of(InlineKeyboardButton.builder()
                .text("Подписка")
                .callbackData("menu:sub")
                .build()));

        rows.add(List.of(InlineKeyboardButton.builder()
                .text("Сменить регион")
                .callbackData("menu:region")
                .build()));

        rows.add(List.of(InlineKeyboardButton.builder()
                .text("Погода на сейчас")
                .callbackData("menu:current")
                .build()));

        rows.add(List.of(InlineKeyboardButton.builder()
                .text("Прогноз на 7 дней")
                .callbackData("menu:forecast")
                .build()));

        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        return message;
    }
}
