package blr.dany.weatheranalyzer.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastDayResponse {

    private String date;
    @JsonProperty("date_epoch")
    private long dateEpoch;
    private DayResponse day;
    private Astro astro;
    private List<HourResponse> hour;

}
