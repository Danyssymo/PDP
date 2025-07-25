package blr.dany.weatherservice.service.impl;

import blr.dany.weatherservice.dto.response.*;
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
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
    private final EntityManager entityManager;

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

    @Transactional
    public List<ForecastDayResponse> getNextDaysForecast(String name) {
        Optional<Location> locationOpt = locationRepository.findByName(name);
        if (locationOpt.isEmpty()) {
            throw new EntityNotFoundException("Location not found");
        }

        Location location = locationOpt.get();

        LocalDate today = LocalDate.now();

        List<ForecastDay> days = weatherDayRepository.findNextDays(location, today);
        return forecastDayMapper.toDtos(days);
    }


    @Transactional
    public void save(ForecastResponse forecastResponse) {
        LocationResponse locationDto = forecastResponse.getLocation();

        Location location = locationRepository.findByName(locationDto.getName())
                .orElseGet(() -> locationRepository.save(locationMapper.toEntity(locationDto)));

        for (ForecastDayResponse forecastDayDto : forecastResponse.getForecast().getForecastDay()) {
            LocalDate date = LocalDate.parse(forecastDayDto.getDate());

            Optional<ForecastDay> existingDayOpt = weatherDayRepository.findByLocationAndDate(location, date);

            ForecastDay forecastDayEntity;
            if (existingDayOpt.isPresent()) {
                forecastDayEntity = existingDayOpt.get();

                if (forecastDayEntity.getHourlyForecasts() != null) {
                    Iterator<HourlyForecast> iterator = forecastDayEntity.getHourlyForecasts().iterator();
                    while (iterator.hasNext()) {
                        HourlyForecast hour = iterator.next();
                        iterator.remove();
                    }
                }
                entityManager.flush();

                updateForecastDayFromDto(forecastDayEntity, forecastDayDto);

            } else {
                forecastDayEntity = forecastDayMapper.toEntity(forecastDayDto);
                forecastDayEntity.setLocation(location);

                if (forecastDayEntity.getHourlyForecasts() != null) {
                    forecastDayEntity.getHourlyForecasts()
                            .forEach(hour -> hour.setForecastDay(forecastDayEntity));
                }
            }

                location.getForecastDays().removeIf(day -> day.getDate().equals(date));
                location.getForecastDays().add(forecastDayEntity);

        }

        locationRepository.save(location);
    }

    @Transactional
    public void updateForecastDayFromDto(ForecastDay entity, ForecastDayResponse dto) {
        forecastDayMapper.updateFromDto(dto, entity);

        List<HourlyForecast> existingHours = entity.getHourlyForecasts();
        List<HourResponse> newHours = dto.getHour();

        if (newHours == null || newHours.isEmpty()) {
            if (existingHours != null) {
                existingHours.clear();
            }
            return;
        }

        if (existingHours == null) {
            entity.setHourlyForecasts(new ArrayList<>());
            existingHours = entity.getHourlyForecasts();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        List<HourlyForecast> hoursToRemove = new ArrayList<>();

        for (HourlyForecast existingHour : existingHours) {
            boolean existsInDto = newHours.stream()
                    .anyMatch(dtoHour -> {
                        LocalDateTime dtoTime = LocalDateTime.parse(dtoHour.getTime(), formatter);
                        return existingHour.getTime().equals(dtoTime);
                    });
            if (!existsInDto) {
                hoursToRemove.add(existingHour);
            }
        }

        existingHours.removeAll(hoursToRemove);

        for (HourResponse hourDto : newHours) {
            LocalDateTime hourTime = LocalDateTime.parse(hourDto.getTime(), formatter);

            Optional<HourlyForecast> match = existingHours.stream()
                    .filter(h -> h.getTime().equals(hourTime))
                    .findFirst();

            if (match.isPresent()) {
                hourlyForecastMapper.updateFromDto(hourDto, match.get());
                match.get().setForecastDay(entity);
            } else {
                HourlyForecast newHourly = hourlyForecastMapper.toEntity(hourDto);
                newHourly.setForecastDay(entity);
                existingHours.add(newHourly);
            }
        }

        existingHours.forEach(h -> h.setForecastDay(entity));
    }
}
