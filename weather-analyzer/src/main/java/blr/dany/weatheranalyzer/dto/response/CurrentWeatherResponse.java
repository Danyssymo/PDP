package blr.dany.weatheranalyzer.dto.response;

import lombok.Data;

@Data
public class CurrentWeatherResponse {

    private LocationResponse location;
    private Current current;

}
