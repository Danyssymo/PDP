package blr.dany.weatherservice.dto.response;

import lombok.Data;

@Data
public class ForecastResponse {

    private LocationResponse location;
    private Current current;
    private Forecast forecast;

}
