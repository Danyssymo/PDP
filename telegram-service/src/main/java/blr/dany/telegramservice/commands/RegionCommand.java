package blr.dany.telegramservice.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class RegionCommand implements CommandHandler {

    @Override
    public void handle(long chatId, Update update) {
        System.out.println("Обработка REGION для чата " + chatId);
    }
}
