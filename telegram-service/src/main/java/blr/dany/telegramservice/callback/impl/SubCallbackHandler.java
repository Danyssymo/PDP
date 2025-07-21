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
public class SubCallbackHandler implements CallbackHandler {

    private final MenuCommand menuCommand;
    private final UserServiceClient userServiceClient;

    @Override
    public boolean supports(String callbackData) {
        return callbackData.startsWith("sub:");
    }

    @Override
    public List<SendMessage> handle(String callbackData, long chatId) {
        String subResult = callbackData.substring("sub:".length());
        if (subResult.equals("yes")) {
            userServiceClient.changeUserSubscription(String.valueOf(chatId), true);
            SendMessage sub = new SendMessage();
            sub.setChatId(chatId);
            sub.setText("Вы успешно подписались");
            SendMessage msg = menuCommand.handle(chatId, null);
            return List.of(sub, msg);
        } else if (subResult.equals("no")){
            userServiceClient.changeUserSubscription(String.valueOf(chatId), false);
            SendMessage sub = new SendMessage();
            sub.setChatId(chatId);
            sub.setText("Вы успешно отписались");
            SendMessage msg = menuCommand.handle(chatId, null);
            return List.of(sub, msg);
        } else {
            SendMessage msg = menuCommand.handle(chatId, null);
            return List.of(msg);
        }
    }
}
