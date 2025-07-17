package blr.dany.telegramservice.commands.impl;

import blr.dany.telegramservice.commands.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommand implements CommandHandler {

    private final RegionCommand regionCommand;
    private final MenuCommand menuCommand;

    @Override
    public SendMessage handle(long chatId, Update update) {

        Boolean flag = true;

        String username = update.getMessage().getFrom().getUserName();
        String greetingMessage = "Привет " + username;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (flag){
            SendMessage regionMessage = regionCommand.handle(chatId, update);
            sendMessage.setText(greetingMessage + "\n\n" + regionMessage.getText());
            sendMessage.setReplyMarkup(regionMessage.getReplyMarkup());
            return sendMessage;
        } else {
            SendMessage menuMessage = menuCommand.handle(chatId, update);
            sendMessage.setText(greetingMessage + "\n" + menuMessage.getText());
            sendMessage.setReplyMarkup(menuMessage.getReplyMarkup());
            return sendMessage;
        }
    }
}
