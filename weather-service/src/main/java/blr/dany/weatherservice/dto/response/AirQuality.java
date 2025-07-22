package blr.dany.weatherservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AirQuality {

    private double co; // угарный газ
    private double no2; // диоксид азота
    private double o3; // озон
    private double so2; // диоксид серы

    @JsonProperty("pm2_5")
    private double pm25; // мелкие частицы

    private double pm10; // крупные частицы

    @JsonProperty("us-epa-index")
    private int usEpaIndex; // индекс качества воздуха по правилам США

    @JsonProperty("gb-defra-index")
    private int gbDefraIndex; // индекс качества воздуха по правилам Британии

}
