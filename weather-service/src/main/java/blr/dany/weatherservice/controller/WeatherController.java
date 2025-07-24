package blr.dany.weatherservice.controller;

import blr.dany.weatherservice.dto.response.CurrentWeatherResponse;
import blr.dany.weatherservice.dto.response.ForecastDayResponse;
import blr.dany.weatherservice.dto.response.ForecastResponse;
import blr.dany.weatherservice.entity.ForecastDay;
import blr.dany.weatherservice.service.impl.WeatherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherServiceImpl weatherService;

    @GetMapping("/current")
    public Mono<CurrentWeatherResponse> getCurrentWeather(@RequestParam String city) {
        return weatherService.getCurrentWeather(city);
    }

    @GetMapping("/forecast")
    public Mono<ForecastResponse> getForecast(@RequestParam String city) {
        return weatherService.getForecast(city,7);
    }

    @GetMapping("/forecast2")
    public List<ForecastDayResponse> getForecast2(@RequestParam String city) {
        List<ForecastDayResponse> res = weatherService.getNextDaysForecast(city);
        System.out.println(res);
        return res;
    }

    @GetMapping("/map")
    public String testMapping(@RequestParam String city) {
        ForecastResponse response = weatherService.getForecast(city, 7).block();
//        weatherService.save(response);
        return "Проверка маппинга выполнена. Смотрите логи в консоли.";
    }
}
