package blr.dany.telegramservice.feign;

import blr.dany.telegramservice.feign.response.CurrentWeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-service", url = "${weather.service.url}")
public interface WeatherServiceClient {

    @GetMapping("/api/v1/weather/current")
    CurrentWeatherResponse getCurrentWeather(@RequestParam String city);

}
