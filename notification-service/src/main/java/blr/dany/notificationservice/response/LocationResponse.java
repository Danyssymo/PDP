package blr.dany.notificationservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationResponse {

    private String name; // название города
    private String region; // регион
    private String country; // страна
    @JsonProperty("lat")
    private double lat; // широта
    @JsonProperty("lon")
    private double lon; // долгота
    @JsonProperty("tz_id")
    private String tzId; // часовой пояс
    private long localtime_epoch; // локальное время
    private String localtime; // тоже локальное время, но в понимаемом формате

}
