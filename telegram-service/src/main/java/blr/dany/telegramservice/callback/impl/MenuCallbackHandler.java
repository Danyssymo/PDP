package blr.dany.telegramservice.callback.impl;

import blr.dany.telegramservice.callback.CallbackHandler;
import blr.dany.telegramservice.commands.impl.MenuCommand;
import blr.dany.telegramservice.commands.impl.RegionCommand;
import blr.dany.telegramservice.commands.impl.SubCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuCallbackHandler implements CallbackHandler {

    private final RegionCommand regionCommand;
    private final MenuCommand menuCommand;
    private final SubCommand subCommand;

    @Override
    public boolean supports(String callbackData) {
        return callbackData.startsWith("menu:");
    }

    @Override
    public List<SendMessage> handle(String callbackData, long chatId) {
        String menuStep = callbackData.substring("menu:".length());
        SendMessage msg;
        if (menuStep.equals("region")) {
            msg = regionCommand.handle(chatId, null);
        } else if (menuStep.equals("sub")) {
            msg = subCommand.handle(chatId, null);
        } else {
            SendMessage errorMessage = new SendMessage();
            errorMessage.setText("Такой комманды не существует");
            errorMessage.setChatId(chatId);
            msg = menuCommand.handle(chatId, null);
            return List.of(errorMessage, msg);
        }
        return List.of(msg);
    }
}
