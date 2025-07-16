package blr.dany.telegramservice.commands.impl;

import blr.dany.telegramservice.commands.CommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuCommand implements CommandHandler {

    @Override
    public SendMessage handle(long chatId, Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        String isSub = "Подписано";
        String urCountry = "Беларусь";

        message.setText("Меню:" + "\n" + "Ваша страна: " + urCountry + "\n" + "Подписка: " + isSub);

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

        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        return message;
    }
}
