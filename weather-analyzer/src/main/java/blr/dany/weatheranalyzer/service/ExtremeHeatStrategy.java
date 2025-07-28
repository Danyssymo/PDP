package blr.dany.weatheranalyzer.service;

import blr.dany.weatheranalyzer.dto.response.HourResponse;
import org.springframework.stereotype.Component;

@Component
public class ExtremeHeatStrategy implements DangerAnalysisStrategy{

    @Override
    public boolean matches(HourResponse hour) {
        return hour.getTempC() > 38;
    }

    @Override
    public DangerLevel getLevel() {
        return DangerLevel.RED;
    }

    @Override
    public String getReason() {
        return "Экстремальная жара > 38°C";
    }
}
