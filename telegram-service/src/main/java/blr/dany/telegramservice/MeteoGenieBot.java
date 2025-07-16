package blr.dany.telegramservice;

import blr.dany.telegramservice.callback.CallbackHandlerFactory;
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

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeteoGenieBot extends TelegramLongPollingBot {

    private final HandleFactory handlerFactory;
    private final CallbackHandlerFactory callbackHandlerFactory;

    @Value("${telegram.bot.name}")
    private String botName;

    @Getter
    @Value("${telegram.bot.token}")
    private String botToken;


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            callbackHandlerFactory.getHandler(callbackData)
                    .ifPresentOrElse(handler -> {
                        List<SendMessage> response = handler.handle(callbackData, chatId);
                        try {
                            for (SendMessage msg :response) {
                                execute(msg);
                            }
                        } catch (TelegramApiException e) {
                            throw new RuntimeException("Ошибка при отправке", e);
                        }
                    }, () -> System.out.println("Нет хендлера для callback: " + callbackData));
            return;
        }

        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        Command command = Command.fromString(messageText.split(" ")[0]);
        if (command != null) {
            CommandHandler handler = handlerFactory.getHandler(command);
            if (handler != null) {
               SendMessage message = handler.handle(chatId, update);
                try {
                        execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

}
