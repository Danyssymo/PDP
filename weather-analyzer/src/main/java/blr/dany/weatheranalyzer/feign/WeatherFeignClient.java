package blr.dany.weatheranalyzer.feign;

import blr.dany.weatheranalyzer.dto.response.ForecastDayResponse;
import blr.dany.weatheranalyzer.dto.response.ForecastResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "weather-service", url = "${weather.service.url}")
public interface WeatherFeignClient {

    @GetMapping("/api/v1/weather/forecast2")
    List<ForecastDayResponse> getNextDayForecast(@RequestParam String city);
}

