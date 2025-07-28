package blr.dany.weatherservice.service;

import blr.dany.weatherservice.dto.response.CurrentWeatherResponse;
import blr.dany.weatherservice.dto.response.ForecastDayResponse;
import blr.dany.weatherservice.dto.response.ForecastResponse;
import blr.dany.weatherservice.entity.ForecastDay;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WeatherService {

    Mono<CurrentWeatherResponse> getCurrentWeather(String city);
    Mono<ForecastResponse> getForecast(String city, int days);
    List<ForecastDayResponse> getNextDaysForecast(String name);
    void save(ForecastResponse forecastResponse);
    void updateForecastDayFromDto(ForecastDay entity, ForecastDayResponse dto);

}
