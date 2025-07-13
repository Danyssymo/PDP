package blr.dany.telegramservice.commands;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component

public class HandleFactory {

    private final Map<Command, CommandHandler> handlers = new HashMap<>();

    public HandleFactory(MessageSender messageSender) {
        register(messageSender);
    }

    public void register (MessageSender messageSender) {
        this.handlers.put(Command.START, new StartCommand(messageSender));
        this.handlers.put(Command.REGION, new RegionCommand());
    }

    public CommandHandler getHandler(Command command) {
        return handlers.get(command);
    }

}
