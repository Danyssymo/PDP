package blr.dany.telegramservice.feign.response;

import lombok.Data;

@Data
public class Location {

    private String name; // название города
    private String region; // регион
    private String country; // страна
    private double lat; // широта
    private double lon; // долгота
    private String tz_id; // часовой пояс
    private long localtime_epoch; // локальное время
    private String localtime; // тоже локальное время, но в понимаемом формате

}
