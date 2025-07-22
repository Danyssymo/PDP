package blr.dany.telegramservice.feign.response;

import lombok.Data;

@Data
public class CurrentWeatherResponse {

    private Location location;
    private Current current;

}
