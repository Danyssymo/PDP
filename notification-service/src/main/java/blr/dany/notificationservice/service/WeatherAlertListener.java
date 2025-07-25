package blr.dany.notificationservice.service;

import blr.dany.notificationservice.response.ForecastDayResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherAlertListener {

    @KafkaListener(
            topics = "weather-alerts",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(ForecastDayResponse forecast) {
        System.out.println("⚠️ Получено сообщение о дне: " + forecast.getDate());
        // Обработка уведомления
    }

}
