package blr.dany.telegramservice.commands;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {

    void handle(long chatId, Update update);

}
