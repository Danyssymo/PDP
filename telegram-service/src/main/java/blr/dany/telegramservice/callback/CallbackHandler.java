package blr.dany.telegramservice.callback;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface CallbackHandler {

    boolean supports(String callbackData);
    List<SendMessage> handle(String callbackData, long chatId);

}
