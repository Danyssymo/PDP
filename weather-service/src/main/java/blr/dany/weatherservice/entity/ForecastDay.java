package blr.dany.weatherservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "forecast_days", schema = "weather_service_schema")
@Data
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

    private Long dateEpoch;

    // Астрономические данные
    private LocalTime sunrise;
    private LocalTime sunset;
    private LocalTime moonrise;
    private LocalTime moonset;

    @Column(length = 50)
    private String moonPhase;

    private Integer moonIllumination;

    // Основные метеоданные
    @Column
    private Double maxTempC;

    @Column
    private Double minTempC;

    @Column
    private Double avgTempC;

    @Column
    private Double maxWindKph;

    @Column
    private Double totalPrecipMm;

    @Column
    private Double avgVisKm;

    private Integer avgHumidity;

    private Boolean dailyWillItRain;

    private Integer dailyChanceOfRain;

    @Column
    private Double uvIndex;

    // Качество воздуха
    private Integer aqiUsEpaIndex;
    private Integer aqiGbDefraIndex;

    // Связи
    @OneToMany(mappedBy = "forecastDay", cascade = CascadeType.ALL, orphanRemoval = true)
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
