package blr.dany.telegramservice.commands.impl;

import blr.dany.telegramservice.commands.CommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RegionCommand implements CommandHandler {

    @Override
    public SendMessage handle(long chatId, Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Выбери регион ");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row1 = Arrays.asList(
                createButton("🇷🇺 Россия", "country:RU"),
                createButton("🇺🇸 США", "country:US")
        );

        List<InlineKeyboardButton> row2 = Arrays.asList(
                createButton("🇩🇪 Германия", "country:DE"),
                createButton("🇨🇳 Китай", "country:CN")
        );

        rows.add(row1);
        rows.add(row2);

        markup.setKeyboard(rows);
        sendMessage.setReplyMarkup(markup);

        return sendMessage;
    }

    private InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
