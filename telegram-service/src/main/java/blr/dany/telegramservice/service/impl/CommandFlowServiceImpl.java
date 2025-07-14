package blr.dany.telegramservice.service.impl;

import blr.dany.telegramservice.commands.Command;
import blr.dany.telegramservice.service.CommandFlowService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class CommandFlowServiceImpl implements CommandFlowService {

    private boolean check = true;

    @Override
    public Command nextStep(long chatId, Update update) {
        if (check) {
            check = false;
            return Command.SET_REGION;
        }
        return null;
    }

}
