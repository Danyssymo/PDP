package blr.dany.weatheranalyzer.kafka;

import blr.dany.weatheranalyzer.dto.request.KafkaAlert;
import blr.dany.weatheranalyzer.dto.response.ForecastDayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForecastKafkaProducer {


    private final KafkaTemplate<String, KafkaAlert> kafkaTemplate;

    private static final String TOPIC = "weather-alerts";

    public void sendYellowAlert(KafkaAlert alert) {
        kafkaTemplate.send(TOPIC, alert.getForecastDayResponse().getDate(), alert);
    }

}
