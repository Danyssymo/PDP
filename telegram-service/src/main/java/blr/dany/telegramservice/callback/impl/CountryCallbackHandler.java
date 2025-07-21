package blr.dany.telegramservice.callback.impl;

import blr.dany.telegramservice.callback.CallbackHandler;
import blr.dany.telegramservice.commands.impl.MenuCommand;
import blr.dany.telegramservice.feign.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryCallbackHandler implements CallbackHandler {

    private final MenuCommand menuCommand;
    private final UserServiceClient userServiceClient;

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
        userServiceClient.changeUserRegion(String.valueOf(chatId), country);
        SendMessage menu = menuCommand.handle(chatId, null);

        return List.of(confirmation, menu);
    }
}
