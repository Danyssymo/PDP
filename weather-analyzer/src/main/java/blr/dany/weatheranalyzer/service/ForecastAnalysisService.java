package blr.dany.weatheranalyzer.service;

import blr.dany.weatheranalyzer.dto.request.KafkaAlert;
import blr.dany.weatheranalyzer.dto.response.AnalyzeDay;
import blr.dany.weatheranalyzer.dto.response.ForecastDayResponse;
import blr.dany.weatheranalyzer.dto.response.ForecastResponse;
import blr.dany.weatheranalyzer.dto.response.HourResponse;
import blr.dany.weatheranalyzer.feign.TelegramFeignClient;
import blr.dany.weatheranalyzer.feign.WeatherFeignClient;
import blr.dany.weatheranalyzer.kafka.ForecastKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForecastAnalysisService {

    private final WeatherFeignClient weatherFeignClient;
    private final ForecastKafkaProducer forecastKafkaProducer;
    private final List<DangerAnalysisStrategy> strategies;
    private final TelegramFeignClient telegramFeignClient;

    private static final List<String> CITIES = Arrays.asList("Minsk", "Vilnius", "Riga", "Moscow");

    @Scheduled(
            fixedRateString = "${analyze.update.fixed-rate-ms}",
            initialDelayString = "${analyze.update.initial-delay-ms}"
    )
    public void analyzeScheduled() {
        log.info("Starting scheduled weather analysis");

        CITIES.forEach(city -> {
            try {
                Map<String, AnalyzeDay> results = analyzeForecast(city);
                log.info("Completed analysis for {}: {} days with alerts",
                        city, results.values().stream()
                                .filter(a -> a.getLevel() != DangerLevel.GREEN)
                                .count());
            } catch (Exception e) {
                log.error("Failed to analyze weather for city: {}", city, e);
            }
        });
    }

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
            KafkaAlert kafkaAlert = new KafkaAlert(day, cityName, analyzed.getMessage());
            if (analyzed.getLevel() == DangerLevel.YELLOW) {
                forecastKafkaProducer.sendYellowAlert(kafkaAlert);
            }
            if (analyzed.getLevel() == DangerLevel.RED) {
               telegramFeignClient.sendAlert(kafkaAlert);
            }
        }

        return result;
    }
}
