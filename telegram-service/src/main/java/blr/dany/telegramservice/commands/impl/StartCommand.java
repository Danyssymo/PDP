package blr.dany.telegramservice.commands.impl;

import blr.dany.telegramservice.commands.CommandHandler;
import blr.dany.telegramservice.feign.UserServiceClient;
import blr.dany.telegramservice.feign.request.CreateUserRequestDto;
import blr.dany.telegramservice.feign.response.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommand implements CommandHandler {

    private final RegionCommand regionCommand;
    private final MenuCommand menuCommand;
    private final UserServiceClient userServiceClient;

    @Override
    public SendMessage handle(long chatId, Update update) {

        String username = update.getMessage().getFrom().getUserName();
        String greetingMessage = "Привет " + username;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        ResponseEntity<TelegramUser> response = userServiceClient.getUserByChatId(String.valueOf(chatId));
        TelegramUser user = response.getBody();

        if (user == null){
            SendMessage regionMessage = regionCommand.handle(chatId, update);
            sendMessage.setText(greetingMessage + "\n\n" + regionMessage.getText());
            sendMessage.setReplyMarkup(regionMessage.getReplyMarkup());
            CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
            createUserRequestDto.setChatId(String.valueOf(chatId));
            createUserRequestDto.setTelegramName(username);
            createUserRequestDto.setIsSub(false);
            userServiceClient.createUser(createUserRequestDto);
            return sendMessage;
        } else if (user.getCountry() == null){
            SendMessage regionMessage = regionCommand.handle(chatId, update);
            sendMessage.setText(greetingMessage + "\n\n" + regionMessage.getText());
            sendMessage.setReplyMarkup(regionMessage.getReplyMarkup());
            return sendMessage;
        } else {
            SendMessage menuMessage = menuCommand.handle(chatId, update);
            sendMessage.setText(greetingMessage + "\n" + menuMessage.getText());
            sendMessage.setReplyMarkup(menuMessage.getReplyMarkup());
            return sendMessage;
        }
    }
}
