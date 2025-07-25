package blr.dany.notificationservice.response;

import lombok.Data;

@Data
public class CurrentWeatherResponse {

    private LocationResponse location;
    private Current current;

}
