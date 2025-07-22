package blr.dany.weatherservice.service.impl;

import blr.dany.weatherservice.dto.response.CurrentWeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl {

    private final WebClient weatherWebClient;

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

}
