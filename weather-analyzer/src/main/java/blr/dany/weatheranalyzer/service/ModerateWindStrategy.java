package blr.dany.weatheranalyzer.service;

import blr.dany.weatheranalyzer.dto.response.HourResponse;
import org.springframework.stereotype.Component;

@Component
public class ModerateWindStrategy implements DangerAnalysisStrategy {

    @Override
    public boolean matches(HourResponse hour) {
        return hour.getWindKph() > 40 && hour.getWindKph() <= 70;
    }

    @Override
    public DangerLevel getLevel() {
        return DangerLevel.YELLOW;
    }

    @Override
    public String getReason() {
        return "Умеренно сильный ветер > 40 km/h";
    }
}
