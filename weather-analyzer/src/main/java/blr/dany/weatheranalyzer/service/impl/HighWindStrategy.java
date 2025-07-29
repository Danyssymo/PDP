package blr.dany.weatheranalyzer.service.impl;

import blr.dany.weatheranalyzer.dto.response.HourResponse;
import blr.dany.weatheranalyzer.service.DangerAnalysisStrategy;
import blr.dany.weatheranalyzer.service.DangerLevel;
import org.springframework.stereotype.Component;

@Component
public class HighWindStrategy implements DangerAnalysisStrategy {

    @Override
    public boolean matches(HourResponse hour) {
        return hour.getWindKph() > 70;
    }

    @Override
    public DangerLevel getLevel() {
        return DangerLevel.RED;
    }

    @Override
    public String getReason() {
        return MessageConstants.HIGH_WIND_STRATEGY_MSG;
    }
}
