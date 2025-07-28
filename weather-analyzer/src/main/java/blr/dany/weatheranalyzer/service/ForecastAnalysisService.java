package blr.dany.weatheranalyzer.service;

import blr.dany.weatheranalyzer.dto.request.KafkaAlert;
import blr.dany.weatheranalyzer.dto.response.AnalyzeDay;
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
    private final List<DangerAnalysisStrategy> strategies;

    private AnalyzeDay analyzeDay(ForecastDayResponse forecastDay) {

        AnalyzeDay analyzeDay = new AnalyzeDay();
        analyzeDay.setLevel(DangerLevel.GREEN);

        for (HourResponse hour : forecastDay.getHour()) {
            for (DangerAnalysisStrategy strategy : strategies) {
                if (strategy.matches(hour)) {
                    analyzeDay.setLevel(strategy.getLevel());
                    analyzeDay.setMessage(strategy.getReason());
                    return analyzeDay;
                }
            }
        }
        return analyzeDay;
    }

    public Map<String, AnalyzeDay> analyzeForecast(String cityName) {
        List<ForecastDayResponse> forecastResponse = weatherFeignClient.getNextDayForecast(cityName);
        Map<String, AnalyzeDay> result = new LinkedHashMap<>();

        for (ForecastDayResponse day : forecastResponse) {
            AnalyzeDay analyzed = analyzeDay(day);
            result.put(day.getDate(), analyzed);
            if (analyzed.getLevel() == DangerLevel.YELLOW) {
                KafkaAlert kafkaAlert = new KafkaAlert(day, cityName, analyzed.getMessage());
                forecastKafkaProducer.sendYellowAlert(kafkaAlert);
            }
        }

        return result;
    }
}
