package blr.dany.telegramservice.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface MessageSender {

    void sendMessage(long chatId, String text);

    void sendMessage(SendMessage message);
}
