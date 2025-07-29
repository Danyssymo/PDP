package blr.dany.weatherservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "weather_conditions", schema = "weather_service_schema")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WeatherCondition {

    @Id
    @Column(name = "code")
    private Integer code;
    @Column(name = "daytime_icon", length = 255)
    private String daytimeIcon;
    @Column(name = "nighttime_icon", length = 255)
    private String nighttimeIcon;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
