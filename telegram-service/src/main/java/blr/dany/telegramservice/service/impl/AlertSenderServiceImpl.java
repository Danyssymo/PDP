package blr.dany.telegramservice.service.impl;

import blr.dany.telegramservice.MeteoGenieBot;
import blr.dany.telegramservice.feign.UserServiceClient;
import blr.dany.telegramservice.feign.response.KafkaAlert;
import blr.dany.telegramservice.feign.response.TelegramUser;
import blr.dany.telegramservice.service.AlertSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertSenderServiceImpl implements AlertSenderService {

    private final MeteoGenieBot bot;
    private final UserServiceClient userServiceClient;

    @Override
    public void sendMessage(KafkaAlert alert) {
        List<TelegramUser> users = userServiceClient.getUsersBySubAndCountry(true, alert.getCity()).getBody();
        for (TelegramUser user : users) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(user.getChatId());
            sendMessage.setText("Вот ты и попался");
            try {
                bot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
