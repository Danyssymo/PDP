package blr.dany.telegramservice.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface CommandHandler {

    SendMessage handle(long chatId, Update update);

}
