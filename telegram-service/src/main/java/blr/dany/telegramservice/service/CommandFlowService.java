package blr.dany.telegramservice.service;

import blr.dany.telegramservice.commands.Command;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandFlowService {

    Command nextStep(long chatId, Update update);

}
