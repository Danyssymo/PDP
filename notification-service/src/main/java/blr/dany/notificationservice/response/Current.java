package blr.dany.notificationservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Current {
    @JsonProperty("last_updated_epoch")
    private long lastUpdatedEpoch; // время обновления данных в самой апи
    @JsonProperty("last_updated")
    private String lastUpdated; // тоже время обновления, но понимаемое
    @JsonProperty("temp_c")
    private double tempC; // темпиратура по цельсию
    @JsonProperty("temp_f")
    private double tempF; // темпиратура по форенгейту
    @JsonProperty("is_day")
    private int isDay; // сейчас день или ночь
    private Condition condition;
    @JsonProperty("wind_mph")
    private double windMph; // скорость ветра мили в час
    @JsonProperty("wind_kph")
    private double windKph; // скорость ветра км в час
    @JsonProperty("wind_degree")
    private int windDegree; // направление ветра в градусах
    @JsonProperty("wind_dir")
    private String windDir; // напралвние ветра по компасу
    @JsonProperty("pressure_mb")
    private double pressureMb; // давление в миллибарах
    @JsonProperty("pressure_in")
    private double pressureIn; // давление в дюймах
    @JsonProperty("precip_mm")
    private double precipMm; // осадки в мм
    @JsonProperty("precip_in")
    private double precipIn; // осадки в дюймах
    private int humidity; // влага %
    private int cloud; // облачность %
    @JsonProperty("feelslike_c")
    private double feelslikeC; // осущается как по C
    @JsonProperty("feelslike_f")
    private double feelslikeF; // осущается как по F
    @JsonProperty("windchill_c")
    private double windchillC; // охлаждающее действие ветра по C
    @JsonProperty("windchill_f")
    private double windchillF; // охлаждающее действие ветра по F
    @JsonProperty("heatindex_c")
    private double heatindexC; // Темп-ра с учётом влажности по С
    @JsonProperty("heatindex_f")
    private double heatindexF; // Темп-ра с учётом влажности по F
    @JsonProperty("dewpoint_c")
    private double dewpointC; // Точка росы по С
    @JsonProperty("dewpoint_f")
    private double dewpointF; // Точка росы по F
    @JsonProperty("vis_km")
    private double visKm; // видимость в км
    @JsonProperty("vis_miles")
    private double visMiles; // видимость в милях
    private double uv; // УФ-индекс
    @JsonProperty("gust_mph")
    private double gustMph; // порывы ветра в милях в час
    @JsonProperty("gust_kph")
    private double gustKph; // порывы ветра в км в час
    @JsonProperty("air_quality")
    private AirQuality airQuality;


}
