package blr.dany.weatherservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "locations", schema = "weather_service_schema")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String region;

    @Column(nullable = false, length = 100)
    private String country;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(length = 50)
    private String timezone;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ForecastDay> forecastDays;
}
