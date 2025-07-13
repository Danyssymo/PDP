package blr.dany.telegramservice.commands;

import lombok.Getter;

@Getter
public enum Command {

    START("/start"),
    REGION("/region");

    private final String commandText;

    Command(String commandText) {
        this.commandText = commandText;
    }

    public static Command fromString(String text) {
        for (Command cmd : Command.values()) {
            if (cmd.commandText.equalsIgnoreCase(text)) {
                return cmd;
            }
        }
        return null;
    }
}
