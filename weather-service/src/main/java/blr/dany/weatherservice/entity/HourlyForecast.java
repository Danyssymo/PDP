package blr.dany.weatherservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "hourly_forecasts", schema = "weather_service_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HourlyForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forecast_day_id", nullable = false)
    private ForecastDay forecastDay;

    @Column(nullable = false)
    private LocalDateTime time;

    private Long timeEpoch;

    // Погодные условия
    @Column
    private Double tempC;

    private Boolean isDay;

    @Column
    private Double windKph;

    private Integer windDegree;

    @Column(length = 3)
    private String windDir;

    @Column
    private Double pressureMb;

    @Column
    private Double precipMm;

    private Integer humidity;
    private Integer cloud;

    @Column
    private Double feelsLikeC;

    private Boolean willItRain;
    private Integer chanceOfRain;

    @Column
    private Double visibilityKm;

    // Качество воздуха
    @Column
    private Double aqiCo;

    @Column
    private Double aqiNo2;

    @Column
    private Double aqiO3;

    @Column
    private Double aqiSo2;

    @Column
    private Double aqiPm25;

    @Column
    private Double aqiPm10;
}

