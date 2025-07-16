package blr.dany.telegramservice.callback.impl;

import blr.dany.telegramservice.callback.CallbackHandler;
import blr.dany.telegramservice.commands.impl.MenuCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryCallbackHandler implements CallbackHandler {

    private final MenuCommand menuCommand;

    @Override
    public boolean supports(String callbackData) {
        return callbackData.startsWith("country:");
    }

    @Override
    public List<SendMessage> handle(String callbackData, long chatId) {
        String country = callbackData.substring("country:".length());
        SendMessage confirmation = new SendMessage();
        confirmation.setChatId(chatId);
        confirmation.setText("Вы выбрали страну: " + country);

        SendMessage menu = menuCommand.handle(chatId, null);

        return List.of(confirmation, menu);
    }
}
