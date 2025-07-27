package blr.dany.weatheranalyzer.service;

import blr.dany.weatheranalyzer.dto.request.KafkaAlert;
import blr.dany.weatheranalyzer.dto.response.ForecastDayResponse;
import blr.dany.weatheranalyzer.dto.response.ForecastResponse;
import blr.dany.weatheranalyzer.dto.response.HourResponse;
import blr.dany.weatheranalyzer.feign.WeatherFeignClient;
import blr.dany.weatheranalyzer.kafka.ForecastKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class ForecastAnalysisService {

    private final WeatherFeignClient weatherFeignClient;
    private final ForecastKafkaProducer forecastKafkaProducer;

    private DangerLevel analyzeDay(ForecastDayResponse forecastDay) {
        for (HourResponse hourly : forecastDay.getHour()) {
            double windKph = hourly.getWindKph();
            double tempC = hourly.getTempC();
            double precipitationMm = hourly.getPrecipMm();

            if (windKph > 70 || tempC > 38 || tempC < -20) {
                return DangerLevel.RED;
            }

            if (windKph > 40 || tempC > 32 || tempC < -10 || precipitationMm > 10) {
                return DangerLevel.YELLOW;
            }
        }

        return DangerLevel.GREEN;
    }

    public Map<String, DangerLevel> analyzeForecast(String cityName) {
        List<ForecastDayResponse> forecastResponse = weatherFeignClient.getNextDayForecast(cityName);
        Map<String, DangerLevel> dangerByDate = new LinkedHashMap<>();

        for (ForecastDayResponse day : forecastResponse) {
            DangerLevel level = analyzeDay(day);
            dangerByDate.put(day.getDate(), level);
            if (level == DangerLevel.YELLOW){
                KafkaAlert kafkaAlert = new KafkaAlert(day, cityName);
                forecastKafkaProducer.sendYellowAlert(kafkaAlert);
            }
        }

        return dangerByDate;
    }
}
