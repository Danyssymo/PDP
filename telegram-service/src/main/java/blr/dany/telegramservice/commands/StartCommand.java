package blr.dany.telegramservice.commands;

import blr.dany.telegramservice.MeteoGenieBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class StartCommand implements CommandHandler {

    private final MessageSender messageSender;

    @Override
    public void handle(long chatId, Update update) {

        String username = update.getMessage().getFrom().getUserName();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Привет " + username);
        messageSender.sendMessage(sendMessage);

    }
}
