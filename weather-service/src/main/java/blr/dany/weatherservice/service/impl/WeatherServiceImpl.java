package blr.dany.weatherservice.service.impl;

import blr.dany.weatherservice.dto.response.CurrentWeatherResponse;
import blr.dany.weatherservice.dto.response.ForecastResponse;
import blr.dany.weatherservice.entity.ForecastDay;
import blr.dany.weatherservice.entity.HourlyForecast;
import blr.dany.weatherservice.entity.Location;
import blr.dany.weatherservice.entity.WeatherCondition;
import blr.dany.weatherservice.mapper.ForecastDayMapper;
import blr.dany.weatherservice.mapper.HourlyForecastMapper;
import blr.dany.weatherservice.mapper.LocationMapper;
import blr.dany.weatherservice.repository.HourlyForecastRepository;
import blr.dany.weatherservice.repository.LocationRepository;
import blr.dany.weatherservice.repository.WeatherConditionRepository;
import blr.dany.weatherservice.repository.WeatherDayRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl {

    private final WebClient weatherWebClient;
    private final WeatherDayRepository weatherDayRepository;
    private final LocationRepository locationRepository;
    private final HourlyForecastRepository hourlyForecastRepository;
    private final WeatherConditionRepository weatherConditionRepository;
    private final LocationMapper locationMapper;
    private final ForecastDayMapper forecastDayMapper;
    private final HourlyForecastMapper hourlyForecastMapper;

    @Value("${weather.api.key}")
    private String apiKey;

    public Mono<CurrentWeatherResponse> getCurrentWeather(String city) {
        return weatherWebClient.get().uri(uriBuilder -> uriBuilder.path("/current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", city)
                        .queryParam("aqi", "yes")
                        .build())
                .retrieve().bodyToMono(CurrentWeatherResponse.class);
    }

    public Mono<ForecastResponse> getForecast(String city, int days) {
        return weatherWebClient.get().uri(uriBuilder -> uriBuilder.path("/forecast.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", city)
                        .queryParam("days", days)
                        .queryParam("aqi", "yes")
                        .queryParam("alerts", "no")
                        .build())
                .retrieve().bodyToMono(ForecastResponse.class);
    }

    public void test(ForecastResponse forecastResponse) {
        // 1. Маппим и выводим локацию
        Location location = locationMapper.toEntity(forecastResponse.getLocation());
        System.out.println("=== ЛОКАЦИЯ ===");
        System.out.printf("Город: %s, Страна: %s%n", location.getName(), location.getCountry());
        System.out.printf("Координаты: %.4f, %.4f%n", location.getLatitude(), location.getLongitude());
        System.out.println("Timezone: " + location.getTimezone());
        System.out.println();

        // 2. Маппим и выводим прогнозы по дням
        System.out.println("=== ДНЕВНЫЕ ПРОГНОЗЫ ===");
        forecastResponse.getForecast().getForecastDay().forEach(dayDto -> {
            ForecastDay forecastDay = forecastDayMapper.toEntity(dayDto);
            System.out.printf("Дата: %s | Мин.темп: %.1f°C | Макс.темп: %.1f°C%n",
                    forecastDay.getDate(),
                    forecastDay.getMinTempC(),
                    forecastDay.getMaxTempC());
            System.out.printf("Восход: %s | Закат: %s%n",
                    forecastDay.getSunrise(),
                    forecastDay.getSunset());
            System.out.printf("Вероятность дождя: %d%%%n", forecastDay.getDailyChanceOfRain());
            System.out.println();

            // 3. Маппим и выводим почасовые прогнозы
            if (dayDto.getHour() != null && !dayDto.getHour().isEmpty()) {
                System.out.println("--- Почасовой прогноз ---");
                dayDto.getHour().forEach(hourDto -> {
                    HourlyForecast hourly = hourlyForecastMapper.toEntity(hourDto);
                    System.out.printf("Время: %s | Темп: %.1f°C | (шанс %d%%)%n",
                            hourly.getTime(),
                            hourly.getTempC(),
                            hourly.getChanceOfRain());
                });
                System.out.println();
            }
        });
    }

    @Transactional
    public void save(ForecastResponse forecastResponse) {
        System.out.println("LESTTTT GOOOO");
        Location location = locationMapper.toEntity(forecastResponse.getLocation());
        List<ForecastDay> forecastDays = forecastResponse.getForecast().getForecastDay().stream()
                .map(forecastDayDto -> {
                    ForecastDay forecastDay = forecastDayMapper.toEntity(forecastDayDto);
                    forecastDay.setLocation(location);
                    if (forecastDay.getHourlyForecasts() != null) {
                        forecastDay.getHourlyForecasts().forEach(hour -> hour.setForecastDay(forecastDay));
                    }
                    return forecastDay;
                })
                .toList();
        location.setForecastDays(forecastDays);
        locationRepository.save(location);

    }

}
