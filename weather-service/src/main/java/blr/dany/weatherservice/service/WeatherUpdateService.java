package blr.dany.weatherservice.service;

import blr.dany.weatherservice.dto.response.ForecastResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherUpdateService {

    private final WeatherService weatherService;
    private final List<String> citiesToUpdate = List.of("Minsk", "Vilnius", "Riga", "Moscow");

    @Scheduled(
            fixedRateString = "${weather.update.fixed-rate-ms}",
            initialDelayString = "${weather.update.initial-delay-ms}"
    )
    public void updateWeatherForAllCities() {
        for (String city : citiesToUpdate) {
            try {
                updateWeatherForCity(city);
            } catch (Exception e) {
                log.error("Failed to update weather for city: {}", city, e);
            }
        }
    }

    public void updateWeatherForCity(String city) {
        ForecastResponse response = weatherService.getForecast(city, 7)
                .onErrorResume(e -> {
                    log.error("Error fetching forecast for city: {}", city, e);
                    return Mono.empty();
                })
                .block();

        if (response != null) {
            weatherService.save(response);
            log.info("Successfully updated weather for city: {}", city);
        }
    }
}