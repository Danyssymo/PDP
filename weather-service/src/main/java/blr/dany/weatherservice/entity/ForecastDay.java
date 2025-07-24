package blr.dany.weatherservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "forecast_days", schema = "weather_service_schema")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private LocalDate date;
    @Column(name = "date_epoch")
    private Long dateEpoch;

    // Астрономические данные
    private LocalTime sunrise;
    private LocalTime sunset;
    private LocalTime moonrise;
    private LocalTime moonset;

    @Column(name = "moon_phase", length = 50)
    private String moonPhase;
    @Column(name = "moon_illumination")
    private Integer moonIllumination;

    // Основные метеоданные
    @Column(name = "maxtemp_c")
    private Double maxTempC;

    @Column(name = "mintemp_c")
    private Double minTempC;

    @Column(name = "avgtemp_c")
    private Double avgTempC;

    @Column(name = "maxwind_kph")
    private Double maxWindKph;

    @Column(name = "totalprecip_mm")
    private Double totalPrecipMm;

    @Column(name = "avgvis_km")
    private Double avgVisKm;
    @Column(name = "avghumidity")
    private Integer avgHumidity;
    @Column(name = "daily_will_it_rain")
    private Integer dailyWillItRain;
    @Column(name = "daily_chance_of_rain")
    private Integer dailyChanceOfRain;

    @Column(name = "uv_index")
    private Double uvIndex;

    // Качество воздуха
    @Column(name = "aqi_us_epa_index")
    private Integer aqiUsEpaIndex;
    @Column(name = "aqi_gb_defra_index")
    private Integer aqiGbDefraIndex;

    // Связи
    @OneToMany(mappedBy = "forecastDay", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HourlyForecast> hourlyForecasts;

    // Даты
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
