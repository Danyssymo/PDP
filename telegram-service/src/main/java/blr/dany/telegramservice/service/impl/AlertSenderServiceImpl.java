package blr.dany.telegramservice.service.impl;

import blr.dany.telegramservice.MeteoGenieBot;
import blr.dany.telegramservice.feign.UserServiceClient;
import blr.dany.telegramservice.feign.response.KafkaAlert;
import blr.dany.telegramservice.feign.response.TelegramUser;
import blr.dany.telegramservice.service.AlertSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertSenderServiceImpl implements AlertSenderService {

    private final MeteoGenieBot bot;
    private final UserServiceClient userServiceClient;



    @Override
    public void sendMessage(KafkaAlert alert) {
        List<TelegramUser> users = userServiceClient.getUsersBySubAndCountry(true, alert.getCity()).getBody();
        for (TelegramUser user : users) {
            String message = String.format("""
        ⚠️ <b>Погодное предупреждение</b>
        
        📍 Город: <b>%s</b>
        📅 Дата: <b>%s</b>
        
        📝 Причина: <i>%s</i>
        
        Будьте осторожны и следите за прогнозом!
        """,
                    alert.getCity(),
                    alert.getForecastDayResponse().getDate(),
                    alert.getMessage()
            );
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(user.getChatId());
            sendMessage.setParseMode("HTML");
            sendMessage.setText(message);
            try {
                bot.execute(sendMessage);
            } catch (TelegramApiException e) {
                log.warn("Ошибка отправки алерта");
                throw new RuntimeException(e);
            }
        }
    }
}
