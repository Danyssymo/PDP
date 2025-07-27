package blr.dany.weatheranalyzer.dto.request;

import blr.dany.weatheranalyzer.dto.response.ForecastDayResponse;
import lombok.Data;

@Data
public class KafkaAlert {

    private final ForecastDayResponse forecastDayResponse;
    private final String city;

}
