package blr.dany.telegramservice.service;

import blr.dany.telegramservice.feign.response.ForecastDayResponse;
import blr.dany.telegramservice.feign.response.KafkaAlert;
import blr.dany.telegramservice.service.impl.AlertSenderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final AlertSenderService alertSenderService;

    @KafkaListener(
            topics = "weather-alerts",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(KafkaAlert alert) {
        System.out.println("⚠️ Получено сообщение о дне (TG): " + alert.getForecastDayResponse().getDate() + " " + alert.getCity());
        alertSenderService.sendMessage(alert);
    }

}
