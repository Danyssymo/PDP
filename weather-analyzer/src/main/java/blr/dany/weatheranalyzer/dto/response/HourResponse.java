package blr.dany.weatheranalyzer.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HourResponse {

    @JsonProperty("time_epoch")
    private long timeEpoch;
    @JsonProperty("time")
    private String time;
    @JsonProperty("temp_c")
    private double tempC;
    @JsonProperty("temp_f")
    private double tempF;
    @JsonProperty("is_day")
    private int isDay;
    private Condition condition;
    @JsonProperty("wind_mph")
    private double windMph;
    @JsonProperty("wind_kph")
    private double windKph;
    @JsonProperty("wind_degree")
    private int windDegree;
    @JsonProperty("wind_dir")
    private String windDir;
    @JsonProperty("pressure_mb")
    private double pressureMb;
    @JsonProperty("pressure_in")
    private double pressureIn;
    @JsonProperty("precip_mm")
    private double precipMm;
    @JsonProperty("precip_in")
    private double precipIn;
    @JsonProperty("snow_cm")
    private double snowCm;
    private int humidity;
    private int cloud;
    @JsonProperty("feelslike_c")
    private double feelsLikeC;
    @JsonProperty("feelslike_f")
    private double feelsLikeF;
    @JsonProperty("windchill_c")
    private double windChillC;
    @JsonProperty("windchill_f")
    private double windChillF;
    @JsonProperty("heatindex_c")
    private double heatIndexC;
    @JsonProperty("heatindex_f")
    private double heatIndexF;
    @JsonProperty("dewpoint_c")
    private double dewPointC;
    @JsonProperty("dewpoint_f")
    private double dewPointF;
    @JsonProperty("will_it_rain")
    private int willItRain;
    @JsonProperty("chance_of_rain")
    private int chanceOfRain;
    @JsonProperty("will_it_snow")
    private int willItSnow;
    @JsonProperty("chance_of_snow")
    private int chanceOfSnow;
    @JsonProperty("vis_km")
    private double visKm;
    @JsonProperty("vis_miles")
    private double visMiles;
    @JsonProperty("gust_mph")
    private double gustMph;
    @JsonProperty("gust_kph")
    private double gustKph;
    private double uv;
    @JsonProperty("air_quality")
    private AirQuality airQuality;

}
