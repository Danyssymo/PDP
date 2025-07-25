package blr.dany.notificationservice.response;

import lombok.Data;

@Data
public class ForecastResponse {

    private LocationResponse location;
    private Current current;
    private Forecast forecast;

}
