package blr.dany.weatheranalyzer.dto.request;

import blr.dany.weatheranalyzer.dto.response.ForecastDayResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KafkaAlert {

    private ForecastDayResponse forecastDayResponse;
    private String city;
    private String message;
}
