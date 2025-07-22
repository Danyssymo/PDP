package blr.dany.weatherservice.dto.response;

import lombok.Data;

@Data
public class CurrentWeatherResponse {

    private Location location;
    private Current current;

}
