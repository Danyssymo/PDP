package blr.dany.telegramservice;

import blr.dany.telegramservice.commands.Command;
import blr.dany.telegramservice.commands.CommandHandler;
import blr.dany.telegramservice.commands.HandleFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class MeteoGenieBot extends TelegramLongPollingBot {

    private final HandleFactory handlerFactory;

    @Value("${telegram.bot.name}")
    private String botName;

    @Getter
    @Value("${telegram.bot.token}")
    private String botToken;



    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        Command command = Command.fromString(messageText.split(" ")[0]);
        if (command != null) {
            CommandHandler handler = handlerFactory.getHandler(command);
            if (handler != null) {
                handler.handle(chatId, update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
