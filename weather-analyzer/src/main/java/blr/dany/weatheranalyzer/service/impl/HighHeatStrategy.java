package blr.dany.weatheranalyzer.service.impl;

import blr.dany.weatheranalyzer.dto.response.HourResponse;
import blr.dany.weatheranalyzer.service.DangerAnalysisStrategy;
import blr.dany.weatheranalyzer.service.DangerLevel;
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
        return MessageConstants.HIGH_HEAT_STRATEGY_MSG;
    }
}
