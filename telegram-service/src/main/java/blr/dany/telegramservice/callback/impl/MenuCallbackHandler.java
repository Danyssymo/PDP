package blr.dany.telegramservice.callback.impl;

import blr.dany.telegramservice.callback.CallbackHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
public class MenuCallbackHandler implements CallbackHandler {

    @Override
    public boolean supports(String callbackData) {
        return callbackData.startsWith("menu:");
    }

    @Override
    public List<SendMessage> handle(String callbackData, long chatId) {
        String menuStep = callbackData.substring("menu:".length());
        SendMessage hello = new SendMessage();
        hello.setChatId(chatId);
        hello.setText("Вот что ты выбрал");
        SendMessage change = new SendMessage();
        change.setChatId(chatId);
        change.setText(menuStep);
        return List.of(hello, change);
    }
}
