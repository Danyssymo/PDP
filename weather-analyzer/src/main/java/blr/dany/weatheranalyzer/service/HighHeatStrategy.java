package blr.dany.weatheranalyzer.service;

import blr.dany.weatheranalyzer.dto.response.HourResponse;
import org.springframework.stereotype.Component;

@Component
public class HighHeatStrategy implements DangerAnalysisStrategy {

    @Override
    public boolean matches(HourResponse hour) {
        return hour.getTempC() > 32 && hour.getTempC() <= 38;
    }

    @Override
    public DangerLevel getLevel() {
        return DangerLevel.YELLOW;
    }

    @Override
    public String getReason() {
        return "Высокая температура > 32°C";
    }
}
