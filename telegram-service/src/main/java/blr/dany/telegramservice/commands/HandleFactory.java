package blr.dany.telegramservice.commands;

import blr.dany.telegramservice.commands.impl.RegionCommand;
import blr.dany.telegramservice.commands.impl.StartCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HandleFactory {

    private final Map<Command, CommandHandler> handlers;

    public HandleFactory(StartCommand startCommand, RegionCommand regionCommand) {
        this.handlers = new HashMap<>();
        this.handlers.put(Command.START, startCommand);
        this.handlers.put(Command.SET_REGION, regionCommand);
    }

    public CommandHandler getHandler(Command command) {
        return handlers.get(command);
    }

}
