package blr.dany.telegramservice;

import blr.dany.telegramservice.commands.Command;
import blr.dany.telegramservice.commands.CommandHandler;
import blr.dany.telegramservice.commands.HandleFactory;
import blr.dany.telegramservice.service.CommandFlowService;
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
    private final CommandFlowService commandFlowService;

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
               var message = handler.handle(chatId, update);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Command nextCommand = commandFlowService.nextStep(chatId, update);
        if (nextCommand != null) {
            CommandHandler nextHandler = handlerFactory.getHandler(nextCommand);
            if (nextHandler != null) {
                SendMessage nextMessages = nextHandler.handle(chatId, update);
                if (nextMessages != null) {
                        try {
                            execute(nextMessages);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                }
            }
        }

    }

    @Override
    public String getBotUsername() {
        return botName;
    }

}
