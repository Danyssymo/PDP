package blr.dany.telegramservice.feign.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaAlert {

    private ForecastDayResponse forecastDayResponse;
    private String city;

}
