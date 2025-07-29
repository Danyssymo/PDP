package blr.dany.weatherservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hourly_forecasts", schema = "weather_service_schema")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class HourlyForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forecast_day_id", nullable = false)
    private ForecastDay forecastDay;
    @Column(nullable = false)
    private LocalDateTime time;
    @Column(name = "time_epoch")
    private Long timeEpoch;
    @Column(name = "temp_c")
    private Double tempC;
    @Column(name = "is_day")
    private Boolean isDay;
    @Column(name = "wind_kph")
    private Double windKph;
    @Column(name = "wind_degree")
    private Integer windDegree;
    @Column(name = "wind_dir", length = 3)
    private String windDir;
    @Column(name = "pressure_mb")
    private Double pressureMb;
    @Column(name = "precip_mm")
    private Double precipMm;
    private Integer humidity;
    private Integer cloud;
    @Column(name = "feelslike_c")
    private Double feelsLikeC;
    @Column(name = "will_it_rain")
    private Integer willItRain;
    @Column(name = "chance_of_rain")
    private Integer chanceOfRain;
    @Column(name = "visibility_km")
    private Double visibilityKm;
    @Column(name = "aqi_co")
    private Double aqiCo;
    @Column(name = "aqi_no2")
    private Double aqiNo2;
    @Column(name = "aqi_o3")
    private Double aqiO3;
    @Column(name = "aqi_so2")
    private Double aqiSo2;
    @Column(name = "aqi_pm2_5")
    private Double aqiPm25;
    @Column(name = "aqi_pm10")
    private Double aqiPm10;
}

