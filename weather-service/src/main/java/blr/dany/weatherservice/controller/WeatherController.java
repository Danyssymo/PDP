package blr.dany.weatherservice.controller;

import blr.dany.weatherservice.dto.response.CurrentWeatherResponse;
import blr.dany.weatherservice.service.impl.WeatherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherServiceImpl weatherService;

    @GetMapping("/current")
    public Mono<CurrentWeatherResponse> getCurrentWeather(@RequestParam String city) {
        var x = weatherService.getCurrentWeather(city);
        System.out.println(x);
        return x;
    }

}
